package ru.uni.service;

import ru.uni.exceptions.UnknownWoodTypeException;
import ru.uni.model.WorkPiece;

import java.util.List;


/**
 * Класс SawmillService представляет собой сервис для обработки массива заготовок древесины
 * и подсчета количества досок, полученных из различных типов древесины.
 *
 * <p>Класс поддерживает работу с тремя типами древесины: сосна, дуб и клен. Результаты
 * обработки записываются в CSV файл.</p>
 *
 * <p>Логирование производится с использованием библиотеки Log4j.</p>
 */
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SawmillProcessor {

    private static final SawmillProcessor INSTANCE = new SawmillProcessor();

    private SawmillProcessor() {}

    public static SawmillProcessor getInstance() {
        return INSTANCE;
    }

    public Map<String, AtomicInteger> saw(List<WorkPiece> workPieces) {
        Map<String, AtomicInteger> boardCounts = new ConcurrentHashMap<>();
        for (WorkPiece workPiece : workPieces) {
            try {
                int boards = getCountBoards(workPiece);
                boardCounts.computeIfAbsent(workPiece.woodType().name(), k -> new AtomicInteger()).addAndGet(boards);
            } catch (UnknownWoodTypeException e) {
                boardCounts.computeIfAbsent("UNKNOWN", k -> new AtomicInteger()).incrementAndGet();
            }
        }

        return boardCounts;
    }

    private int getCountBoards(WorkPiece workPiece) {
        return workPiece.getDiameter()
                .map(d -> d.getBoardsPerTwoMeters() * (workPiece.getLength() / 2))
                .orElse(0);
    }
}


