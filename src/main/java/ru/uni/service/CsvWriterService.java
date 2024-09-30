package ru.uni.service;

import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class CsvWriterService {


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

            log.info("Результаты успешно записаны в CSV файл: " + csvFile);
        } catch (IOException e) {
            log.error("Ошибка при записи в CSV файл: " + csvFile, e);
        }
    }
}

