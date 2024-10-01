package ru.uni.service;

import lombok.extern.slf4j.Slf4j;
import ru.uni.model.WorkPiece;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ConcurrentSawmillProcessor {


    private final ExecutorService executorService;
    private static final SawmillProcessor sawmillService = SawmillProcessor.getInstance();
    private final CsvWriterService csvWriterService = new CsvWriterService();
    private static final int THREAD_POOL_SIZE = 2;

    public ConcurrentSawmillProcessor() {
        this.executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    public void processWorkpiecesConcurrently(List<List<WorkPiece>> partitionedWorkPieces) {
        try {
            for (List<WorkPiece> workPieces : partitionedWorkPieces) {
                createTask(workPieces);
            }
        } catch (Exception e) {
            log.error("Ошибка при обработке заготовок", e);
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
    }

    private void createTask(List<WorkPiece> workPieces) {
        executorService.submit(() -> {
            Map<String, AtomicInteger> boardCounts = sawmillService.saw(workPieces);

            csvWriterService.writeBoardCountsToCsv(boardCounts);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error("Ошибка при паузе потока", e);
                Thread.currentThread().interrupt();
            }
        });
    }
}
