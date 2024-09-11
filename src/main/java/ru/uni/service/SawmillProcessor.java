package ru.uni.service;

import ru.uni.exceptions.UnknownWoodTypeException;
import ru.uni.model.WorkPiece;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SawmillProcessor {

    private static final SawmillProcessor INSTANCE = new SawmillProcessor();

    private SawmillProcessor() {
    }

    public static SawmillProcessor getInstance() {
        return INSTANCE;
    }

    public Map<String, AtomicInteger> saw(List<WorkPiece> workPieces) {
        return workPieces.stream()
                .collect(Collectors.toConcurrentMap(
                        workPiece -> {
                            try {
                                return workPiece.woodType().name();
                            } catch (UnknownWoodTypeException e) {
                                return "UNKNOWN";
                            }
                        },
                        workPiece -> new AtomicInteger(getCountBoards(workPiece)),
                        (a, b) -> {
                            a.addAndGet(b.get());
                            return a;
                        }
                ));
    }


    private int getCountBoards(WorkPiece workPiece) {
        return workPiece.getDiameter()
                .map(d -> d.getBoardsPerTwoMeters() * (workPiece.getLength() / 2))
                .orElse(0);
    }
}


