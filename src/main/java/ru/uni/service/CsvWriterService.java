package ru.uni.service;

import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import ru.uni.model.Tree;
import ru.uni.model.WorkPiece;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class CsvWriterService {

    public void writeBoardCountsToCsv(Map<String, Integer> boardCounts) {
        String csvFile = "result_" + Thread.currentThread().getId() + ".csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            String[] header = {"Wood Type", "Boards"};
            writer.writeNext(header);

            for (Map.Entry<String, Integer> entry : boardCounts.entrySet()) {
                String[] data = {entry.getKey(), String.valueOf(entry.getValue())};
                writer.writeNext(data);
            }

            log.info("Результаты успешно записаны в CSV файл: " + csvFile);
        } catch (IOException e) {
            log.error("Ошибка при записи в CSV файл: " + csvFile, e);
        }
    }

    public void writeFailedWorkPiecesToCsv(List<WorkPiece> failedWorkPieces) {
        String csvFile = "failed_workpieces_" + Thread.currentThread().getId() + ".csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            // Заголовок CSV файла
            String[] header = {"Length", "Diameter", "Wood Type"};
            writer.writeNext(header);

            for (WorkPiece workPiece : failedWorkPieces) {
                String[] data = {
                        String.valueOf(workPiece.getLength()),
                        String.valueOf(workPiece.getDiameter().orElse(null)),
                        workPiece instanceof Tree ? ((Tree) workPiece).getWoodType() : "Unknown"
                };
                writer.writeNext(data);
            }

            log.info("Неудачные заготовки записаны в CSV файл: " + csvFile);
        } catch (IOException e) {
            log.error("Ошибка при записи неудачных заготовок в CSV файл: " + csvFile, e);
        }
    }
}



