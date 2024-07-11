package ru.uni.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.log4j.Logger;
import ru.uni.model.Tree;

import java.io.FileReader;
import java.io.IOException;

/**
 * Класс ReaderService предназначен для чтения данных из CSV файла и создания массива объектов Tree.
 */
public class ReaderService {

    private static final Logger logger = Logger.getLogger(ReaderService.class);

    /**
     * Читает данные из CSV файла и создает массив объектов Tree.
     *
     * @return массив объектов Tree, прочитанных из CSV файла.
     */
    public Tree[] readTrees() {
        String csvFileR = "fileToRead.csv";

        int numLines = 4;
        Tree[] trees = new Tree[numLines];

        try (CSVReader csvReader = new CSVReader(new FileReader(csvFileR))) {
            String[] line;
            int index = 0;
            while ((line = csvReader.readNext()) != null && index < numLines) {
                int length = Integer.parseInt(line[0].trim());
                int diameter = Integer.parseInt(line[1].trim());
                String type = line[2].trim().toUpperCase();
                trees[index] = new Tree(length, diameter, type);
                index++;
            }
            logger.info("Успешно прочитаны данные из файла: " + csvFileR);
        } catch (IOException e) {
            logger.error("Ошибка при чтении CSV файла", e);
        } catch (CsvValidationException e) {
            logger.error("Ошибка валидации CSV файла", e);
        }

        logger.info("Чтение данных из файла завершено.");
        return trees;
    }
}

//package ru.uni.service;
//
//import com.opencsv.CSVReader;
//import com.opencsv.exceptions.CsvValidationException;
//import org.apache.log4j.Logger;
//import ru.uni.model.Tree;
//
//import java.io.FileReader;
//import java.io.IOException;
//
///**
// * Класс ReaderService предназначен для чтения данных из CSV файла и создания массива объектов Tree.
// */
//public class ReaderService {
//
//    private static final Logger logger = Logger.getLogger(ReaderService.class);
//    /**
//     * Читает данные из CSV файла и создает массив объектов Tree.
//     *
//     * @return массив объектов Tree, прочитанных из CSV файла.
//     */
//    public Tree[] readTrees() {
//        String csvFileR = "fileToRead.csv";
//
//        int numLines = 4;
//        Tree[] trees = new Tree[numLines];
//
//        try (
//                CSVReader csvReader = new CSVReader(new FileReader(csvFileR))) {
//            String[] line;
//            int index = 0;
//            while ((line = csvReader.readNext()) != null && index < numLines) {
//                int length = Integer.parseInt(line[0].trim());
//                int diameter = Integer.parseInt(line[1].trim());
//                String type = line[2].trim().toUpperCase();
//                trees[index] = new Tree(length, diameter, type);
//                index++;
//            }
//            logger.info("Successfully read trees from file: " + csvFileR);
//        } catch (
//                IOException e) {
//            logger.error("Error reading CSV file", e);
//        } catch (
//                CsvValidationException e) {
//            logger.error("CSV validation error", e);
//        }
//        logger.info("Reading trees from file: " + csvFileR);
//        return trees;
//    }
//}
