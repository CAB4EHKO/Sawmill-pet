package ru.uni.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import ru.uni.model.Tree;
import ru.uni.model.WorkPiece;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс ReaderService предназначен для чтения данных из CSV файла и создания массива объектов Tree.
 */

public class CsvReaderService {
    /**
     * Читает данные из CSV файла и создает список объектов Tree.
     *
     * @return список объектов Tree, прочитанных из CSV файла.
     */

    public List<WorkPiece> readWorkPiecesFromCsv() {
        String csvFileR = "fileToRead.csv";
        List<WorkPiece> workPieces = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFileR))) {
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                int length = Integer.parseInt(line[0].trim());
                int diameter = Integer.parseInt(line[1].trim());
                String type = line[2].trim().toUpperCase();
                workPieces.add(new Tree(length, diameter, type));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException();
        }
        return workPieces;
    }
}