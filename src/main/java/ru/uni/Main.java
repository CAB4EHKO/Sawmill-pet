package ru.uni;

import ru.uni.model.Tree;
import ru.uni.model.WorkPiece;
import ru.uni.service.ConcurrentSawmillProcessor;
import ru.uni.service.CsvReaderService;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CsvReaderService readerService = new CsvReaderService();

        // Читаем заготовки древесины из файла
        List<WorkPiece> workPieces = readerService.readWorkPiecesFromCsv();
        List<WorkPiece> workPieces2 = List.of(
                new Tree(4,200,"PINE"),
                new Tree(6,500,"OAK"),
                new Tree(8,700,"MAPLE")
        );

        // Разбиваем список на подсписки для многопоточной обработки (например, на 2 части)
        List<List<WorkPiece>> partitionedWorkPieces = Arrays.asList(workPieces, workPieces2);
//        partitionedWorkPieces.add(workPieces);

        // Создаем экземпляр ConcurrentSawmillProcessor для многопоточной обработки
        ConcurrentSawmillProcessor sawmillProcessor = new ConcurrentSawmillProcessor();

        // Запускаем многопоточную обработку заготовок
        sawmillProcessor.processWorkpiecesConcurrently(partitionedWorkPieces);
    }
}
