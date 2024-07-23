package ru.uni.service;

import com.opencsv.CSVWriter;
import org.apache.log4j.Logger;
import ru.uni.enums.Diameter;
import ru.uni.model.WorkPiece;

import java.io.FileWriter;
import java.io.IOException;


/**
 * Класс SawmillService представляет собой сервис для обработки массива заготовок древесины
 * и подсчета количества досок, полученных из различных типов древесины.
 *
 * <p>Класс поддерживает работу с тремя типами древесины: сосна, дуб и клен. Результаты
 * обработки записываются в CSV файл.</p>
 *
 * <p>Логирование производится с использованием библиотеки Log4j.</p>
 */
public class SawmillService {

    private static final Logger logger = Logger.getLogger(SawmillService.class);

    // Количество досок для каждого типа древесины
    private int pineBoards = 0;
    private int oakBoards = 0;
    private int mapleBoards = 0;
    private int unknownWoodCount = 0;

    /**
     * Обрабатывает массив заготовок древесины, подсчитывая количество досок
     * для каждого типа древесины и записывая результаты в CSV файл.
     *
     * @param workPieces массив заготовок древесины
     */
    public void saw(WorkPiece[] workPieces) {
        logger.info("Начало обработки массива заготовок.");

        for (WorkPiece workPiece : workPieces) {
            int boards = getCountBoards(workPiece);
            try {
                switch (workPiece.woodType()) {
                    case PINE:
                        pineBoards += boards;
                        logger.info("Добавлено " + boards + " досок сосны.");
                        break;
                    case OAK:
                        oakBoards += boards;
                        logger.info("Добавлено " + boards + " досок дуба.");
                        break;
                    case MAPLE:
                        mapleBoards += boards;
                        logger.info("Добавлено " + boards + " досок клена.");
                        break;
                }
            } catch (IllegalArgumentException e) {
                unknownWoodCount++;
                logger.warn("Обнаружен неизвестный тип древесины: ");
            }
        }

        logger.info("Обработка завершена. Сосна: " + pineBoards + ", Дуб: " + oakBoards +
                ", Клен: " + mapleBoards + ", Неизвестный тип: " + unknownWoodCount);

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

            logger.info("Результаты успешно записаны в CSV файл: " + csvFile);
        } catch (IOException e) {
            logger.error("Ошибка при записи в CSV файл", e);
        }
    }

    /**
     * Подсчитывает количество досок, которое можно получить из заданной заготовки древесины.
     *
     * @param workPiece заготовка древесины
     * @return количество досок
     */
    private int getCountBoards(WorkPiece workPiece) {
        Diameter diameter = workPiece.getDiameter();
        int boardsCountByDiameter = diameter.getBoardsPerTwoMeters();
        int count = boardsCountByDiameter * (workPiece.getLength() / 2);
        logger.debug("Количество досок для заготовки с диаметром " + diameter + ": " + count);
        return count;
    }
}
