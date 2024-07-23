package ru.uni.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import ru.uni.model.Tree;
import ru.uni.model.WorkPiece;

import java.io.FileReader;
import java.io.IOException;

/**
 * Класс ReaderService предназначен для чтения данных из CSV файла и создания массива объектов Tree.
 */
public class ReaderService {

    /**
     * Читает данные из CSV файла и создает массив объектов Tree.
     *
     * @return массив объектов Tree, прочитанных из CSV файла.
     */
    public WorkPiece[] readWorkPieces() {

        String csvFileR = "fileToRead.csv";
        int numLines = 5;

        WorkPiece[] workPieces = new WorkPiece[numLines];
        try (
                CSVReader csvReader = new CSVReader(new FileReader(csvFileR))) {
            String[] line;
            int index = 0;
            while ((line = csvReader.readNext()) != null && index < numLines) {
                int length = Integer.parseInt(line[0].trim());
                int diameter = Integer.parseInt(line[1].trim());
                String type = line[2].trim().toUpperCase();
                workPieces[index] = new Tree(length, diameter, type);
                index++;
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (
                CsvValidationException e) {
            throw new RuntimeException();
        }
        return workPieces;
    }
}