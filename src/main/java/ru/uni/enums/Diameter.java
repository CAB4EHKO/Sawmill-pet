package ru.uni.enums;


import java.util.Arrays;
import java.util.Optional;

public enum Diameter {

    SMALL(200, 3),
    MEDIUM(500, 7),
    LARGE(700, 12);

    private final int diameter;
    private final int boardPerTwoMeters;

    Diameter(int diameter, int boardPerTwoMeters) {
        this.diameter = diameter;
        this.boardPerTwoMeters = boardPerTwoMeters;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getBoardsPerTwoMeters() {
        return boardPerTwoMeters;
    }
}
