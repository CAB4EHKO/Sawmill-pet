package ru.uni.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.uni.dto.BatchWorkPiece;
import ru.uni.dto.SawResult;
import ru.uni.dto.WorkPieceDto;
import ru.uni.entity.FileProcessingStatus;
import ru.uni.entity.SawmillResultEntity;
import ru.uni.entity.SawmillResultItemEntity;
import ru.uni.enums.FileStatus;
import ru.uni.model.Tree;
import ru.uni.model.WorkPiece;
import ru.uni.repositories.FileProcessingStatusRepository;
import ru.uni.repositories.SawmillResultEntityRepository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileProcessingService {

    private final FileProcessingStatusRepository statusRepository;
    private final SawmillProcessor sawmillProcessor;
    private final SawmillResultEntityRepository resultRepository;

    public FileProcessingService(FileProcessingStatusRepository statusRepository,
                                 SawmillProcessor sawmillProcessor,
                                 SawmillResultEntityRepository resultRepository) {
        this.statusRepository = statusRepository;
        this.sawmillProcessor = sawmillProcessor;
        this.resultRepository = resultRepository;
    }

    @Async
    public void processFileAsync(MultipartFile file, FileProcessingStatus status) {
        try {
            InputStream inputStream = file.getInputStream();
            List<BatchWorkPiece> batches = readBatchesFromCsv(inputStream);

            int totalBatches = batches.size();
            int processedBatches = 0;
            List<String> failedBatchNumbers = new ArrayList<>();
            Map<String, Integer> failedWorkPiecesPerBatch = new HashMap<>();

            for (BatchWorkPiece batch : batches) {
                int failedWorkPiecesCount = processBatch(batch, failedBatchNumbers);
                if (failedWorkPiecesCount < batch.getWorkPieces().size()) {
                    processedBatches++;
                }
                if (failedWorkPiecesCount > 0) {
                    failedWorkPiecesPerBatch.put(batch.getBatchNumber(), failedWorkPiecesCount);
                }
            }

            if (failedWorkPiecesPerBatch.isEmpty()) {
                status.setStatus(FileStatus.PROCESSED);
                status.setComment(null);
            } else {
                status.setStatus(FileStatus.PARTIALLY_PROCESSED);

                StringBuilder commentBuilder = new StringBuilder();
                commentBuilder.append(String.format("Обработано %d партий из %d.\n", processedBatches, totalBatches));

                for (Map.Entry<String, Integer> entry : failedWorkPiecesPerBatch.entrySet()) {
                    String batchNumber = entry.getKey();
                    int failedCount = entry.getValue();
                    commentBuilder.append(String.format("В партии %s не обработано заготовок: %d .", batchNumber, failedCount));
                }

                status.setComment(commentBuilder.toString());
            }
        } catch (Exception e) {
            log.error("Ошибка при обработке файла {}", file.getOriginalFilename(), e);
            status.setStatus(FileStatus.FAILED);
            status.setComment("Ошибка при обработке файла: " + e.getMessage());
        } finally {
            statusRepository.save(status);
        }
    }

    private List<BatchWorkPiece> readBatchesFromCsv(InputStream inputStream) throws IOException {
        List<BatchWorkPiece> batches = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
            String[] line;
            BatchWorkPiece currentBatch = null;

            while ((line = reader.readNext()) != null) {
                if (line.length == 0 || line[0].trim().isEmpty()) {
                    continue;
                }

                if (line[0].equalsIgnoreCase("BatchNumber")) {
                    String batchNumber = line[1].trim();
                    currentBatch = new BatchWorkPiece(batchNumber, new ArrayList<>());
                    batches.add(currentBatch);
                } else {
                    int length = Integer.parseInt(line[0].trim());
                    int diameter = Integer.parseInt(line[1].trim());
                    String woodType = line[2].trim();

                    WorkPieceDto dto = new WorkPieceDto();
                    dto.setLength(length);
                    dto.setDiameter(diameter);
                    dto.setWoodType(woodType);

                    if (currentBatch != null) {
                        currentBatch.getWorkPieces().add(dto);
                    } else {
                        throw new IllegalArgumentException("Данные заготовки вне партии");
                    }
                }
            }
        } catch (CsvValidationException e) {
            throw new IOException("Неверный формат CSV файла", e);
        }
        return batches;
    }

    private int processBatch(BatchWorkPiece batch, List<String> failedBatchNumbers) {
        List<WorkPiece> workPieces = new ArrayList<>();

        for (WorkPieceDto dto : batch.getWorkPieces()) {
            WorkPiece workPiece = new Tree(dto.getLength(), dto.getDiameter(), dto.getWoodType());
            workPieces.add(workPiece);
        }

        SawResult result = sawmillProcessor.saw(workPieces);

        int totalWorkPieces = workPieces.size();
        int failedWorkPiecesCount = result.getFailedWorkPieces().size();
        int successfulWorkPiecesCount = totalWorkPieces - failedWorkPiecesCount;

        if (successfulWorkPiecesCount > 0) {
            Map<String, Integer> resultMap = result.getBoardCounts().entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get()));

            SawmillResultEntity resultEntity = new SawmillResultEntity();
            resultEntity.setBatchNumber(batch.getBatchNumber());

            List<SawmillResultItemEntity> items = resultMap.entrySet().stream()
                    .map(e -> {
                        SawmillResultItemEntity item = new SawmillResultItemEntity();
                        item.setWoodType(e.getKey());
                        item.setBoardCount(e.getValue());
                        item.setResult(resultEntity);
                        return item;
                    })
                    .collect(Collectors.toList());

            resultEntity.setItems(items);

            resultRepository.save(resultEntity);

            if (failedWorkPiecesCount > 0) {
                log.warn("В партии {} не обработано {} заготовок из {}.", batch.getBatchNumber(), failedWorkPiecesCount, totalWorkPieces);
            }
            return failedWorkPiecesCount;
        } else {
            failedBatchNumbers.add(batch.getBatchNumber());
            log.error("Все заготовки в партии {} не были обработаны.", batch.getBatchNumber());
            return totalWorkPieces;
        }
    }
}
