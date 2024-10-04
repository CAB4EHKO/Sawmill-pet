package ru.uni.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.uni.dto.SawResult;
import ru.uni.exceptions.UnknownWoodTypeException;
import ru.uni.model.WorkPiece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class SawmillProcessor {

    private static final SawmillProcessor INSTANCE = new SawmillProcessor();

    private SawmillProcessor() {
    }

    public static SawmillProcessor getInstance() {
        return INSTANCE;
    }

    public SawResult saw(List<WorkPiece> workPieces) {
        Map<String, AtomicInteger> boardCounts = new ConcurrentHashMap<>();
        List<WorkPiece> failedWorkPieces = new ArrayList<>();

        workPieces.forEach(workPiece -> {
            try {
                String woodTypeName = workPiece.woodType().name();
                int count = getCountBoards(workPiece);
                boardCounts.merge(woodTypeName, new AtomicInteger(count), (a, b) -> {
                    a.addAndGet(b.get());
                    return a;
                });
            } catch (UnknownWoodTypeException e) {
                log.error("Неизвестный тип древесины: {}", workPiece);
                failedWorkPieces.add(workPiece);
            }
        });

        SawResult result = new SawResult();
        result.setBoardCounts(boardCounts);
        result.setFailedWorkPieces(failedWorkPieces);
        return result;
    }

    private int getCountBoards(WorkPiece workPiece) {
        return workPiece.getDiameter()
                .map(d -> d.getBoardsPerTwoMeters() * (workPiece.getLength() / 2))
                .orElse(0);
    }
}


