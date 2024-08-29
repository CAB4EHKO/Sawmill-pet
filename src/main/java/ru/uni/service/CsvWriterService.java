package ru.uni.service;

import com.opencsv.CSVWriter;
import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;
import org.apache.log4j.Logger;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CsvWriterService {
    private static final Logger logger = Logger.getLogger(CsvWriterService.class);

    public void writeBoardCountsToCsv(Map<String, AtomicInteger> boardCounts) {
        String csvFile = "result_" + Thread.currentThread().getId() + ".csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            // Заголовок CSV файла
            String[] header = {"Wood Type", "Boards"};
            writer.writeNext(header);

            for (Map.Entry<String, AtomicInteger> entry : boardCounts.entrySet()) {
                String[] data = {entry.getKey(), String.valueOf(entry.getValue())};
                writer.writeNext(data);
            }

            logger.info("Результаты успешно записаны в CSV файл: " + csvFile);
        } catch (IOException e) {
            logger.error("Ошибка при записи в CSV файл", e);
        }
    }
}

