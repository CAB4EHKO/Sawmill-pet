package ru.uni;

import ru.uni.model.WorkPiece;
import ru.uni.service.ConcurrentSawmillProcessor;
import ru.uni.service.CsvReaderService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CsvReaderService readerService = new CsvReaderService();

        // Читаем заготовки древесины из файла
        List<WorkPiece> workPieces = readerService.readWorkPiecesFromCsv();

        // Разбиваем список на подсписки для многопоточной обработки (например, на 2 части)
        List<List<WorkPiece>> partitionedWorkPieces = new ArrayList<>();
        partitionedWorkPieces.add(workPieces);

        // Создаем экземпляр ConcurrentSawmillProcessor для многопоточной обработки
        ConcurrentSawmillProcessor sawmillProcessor = new ConcurrentSawmillProcessor();

        // Запускаем многопоточную обработку заготовок
        sawmillProcessor.processWorkpiecesConcurrently(partitionedWorkPieces);
    }
}
