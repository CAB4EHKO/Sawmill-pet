package ru.uni.service;

import com.opencsv.CSVWriter;
import ru.uni.enums.WoodType;
import ru.uni.exceptions.UnknownWoodTypeException;
import ru.uni.model.Tree;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс SawmillService предназначен для обработки массива объектов Tree
 * с целью определения количества досок, которые можно получить из каждого типа древесины.
 * Он ведет учет общего количества досок для сосны, дуба и клена, а также фиксирует количество неизвестных типов древесины.
 * Результаты записываются в CSV файл.
 */
public class SawmillService {
    private int pineBoards = 0;
    private int oakBoards = 0;
    private int mapleBoards = 0;
    private int unknownWoodCount = 0;

    /**
     * Обрабатывает массив объектов Tree для вычисления количества досок каждого типа древесины и записывает результаты в CSV файл.
     *
     * @param trees массив объектов Tree, которые необходимо обработать.
     */
    public void saw(Tree[] trees) {
        for (Tree tree : trees) {
            try {
                int boards = tree.getBoardsPerTwoMeters() * (tree.getLength() / 2);
                WoodType woodType = tree.getWoodType();

                switch (woodType) {
                    case PINE:
                        pineBoards += boards;
                        break;
                    case OAK:
                        oakBoards += boards;
                        break;
                    case MAPLE:
                        mapleBoards += boards;
                        break;
                }
            } catch (UnknownWoodTypeException e) {
                unknownWoodCount++;
            }
        }

        // Запись результатов в CSV файл
        String csvFile = "fileToWrite.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            // Заголовок CSV файла
            String[] header = {"Wood Type", "Boards"};
            writer.writeNext(header);

            // Данные для каждого типа древесины
            String[] pineData = {"PINE", String.valueOf(pineBoards)};
            writer.writeNext(pineData);

            String[] oakData = {"OAK", String.valueOf(oakBoards)};
            writer.writeNext(oakData);

            String[] mapleData = {"MAPLE", String.valueOf(mapleBoards)};
            writer.writeNext(mapleData);

            String[] unknownData = {"UNKNOWN", String.valueOf(unknownWoodCount)};
            writer.writeNext(unknownData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

