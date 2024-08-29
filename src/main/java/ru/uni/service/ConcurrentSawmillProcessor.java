package ru.uni.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.log4j.Logger;
import ru.uni.model.WorkPiece;


public class ConcurrentSawmillProcessor {

    private static final Logger logger = Logger.getLogger(ConcurrentSawmillProcessor.class);

    private final ExecutorService executorService;
    private static final SawmillProcessor sawmillService = SawmillProcessor.getInstance();
    private final CsvWriterService csvWriterService = new CsvWriterService();
    private static final int THREAD_POOL_SIZE = 2;

    // Потокобезопасная коллекция для синхронизации результатов между потоками

    public ConcurrentSawmillProcessor() {
        this.executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    public void processWorkpiecesConcurrently(List<List<WorkPiece>> partitionedWorkPieces) {
        try {
            for (List<WorkPiece> workPieces : partitionedWorkPieces) {
                // Делаем разбиение внутри каждого подсписка
                int chunkSize = (int) Math.ceil((double) workPieces.size() / THREAD_POOL_SIZE);

                for (int i = 0; i < workPieces.size(); i += chunkSize) {
                    List<WorkPiece> chunk = workPieces.subList(i, Math.min(workPieces.size(), i + chunkSize));
                    createTask(chunk);
                }
            }
        } catch (Exception e) {
            logger.error("Ошибка при обработке заготовок", e);
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
    }

    private void createTask(List<WorkPiece> workPieces) {
        executorService.submit(() -> {
            // Вызов SawmillService для обработки заготовок
            Map<String, AtomicInteger> boardCounts = sawmillService.saw(workPieces);

            // Вызов WriterService для записи результатов
            csvWriterService.writeBoardCountsToCsv(boardCounts);

            // Долгая операция для примера
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.error("Ошибка при паузе потока", e);
                Thread.currentThread().interrupt();
            }
        });
    }
}

//public class MultithreadedSawmillService {
//
//    private static final Logger logger = Logger.getLogger(MultithreadedSawmillService.class);
//
//    private final ExecutorService executorService;
//    // TODO строку ниже не менять
//    private static final SawmillService sawmillService = SawmillService.getInstance();
//    private final WriterService writerService = new WriterService();
//    private static final int THREAD_POOL_SIZE = 2;
//
//    // Потокобезопасная коллекция для синхронизации результатов между потоками
//    private final ConcurrentHashMap<String, AtomicInteger> boardCounts = new ConcurrentHashMap<>();
//
//    public MultithreadedSawmillService() {
//        this.executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
//    }
//
//    public void multithreadedSaw(List<List<WorkPiece>> workPieces) {
//        try {
//            // Синхронизация результата между потоками
//            for (List<WorkPiece> workPiece : workPieces) {
//                createTask(workPiece);
//            }
//        } catch (Exception e) {
//            logger.error("Ошибка при обработке заготовок", e);
//            throw new RuntimeException(e);
//        } finally {
//            executorService.shutdown();
//        }
//    }
//
//    private void createTask(List<WorkPiece> workPieces) {
//        executorService.submit(() -> {
//            // Вызов SawmillService для обработки заготовок
//            sawmillService.saw(workPieces, boardCounts);
//
//            // Вызов WriterService для записи результатов
//            writerService.writer(boardCounts);
//
//            // Долгая операция для примера
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                logger.error("Ошибка при паузе потока", e);
//                Thread.currentThread().interrupt();
//            }
//        });
//    }
//}
