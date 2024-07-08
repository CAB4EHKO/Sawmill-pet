package ru.uni.enums;

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

    public static Diameter fromDiameter(int diameter) {
        for (Diameter d : values()) {
            if (d.getDiameter() == diameter) {
                return d;
            }
        }
        return null;
    }
}
