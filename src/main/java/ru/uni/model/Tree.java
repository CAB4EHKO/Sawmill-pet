package ru.uni.model;

import ru.uni.enums.Diameter;
import ru.uni.enums.WoodType;
import ru.uni.exceptions.UnknownWoodTypeException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;


public class Tree implements WorkPiece {

    private final int length;
    private final int diameter;
    private final String woodType;

    public Tree(int length, int diameter, String woodType) {
        this.length = length;
        this.diameter = diameter;
        this.woodType = woodType;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public Optional<Diameter> getDiameter() {
        return Arrays.stream(Diameter.values())
                .min(Comparator.comparingInt(d -> Math.abs(d.getDiameter() - diameter)));
    }

    @Override
    public WoodType woodType() {
        try {
            return WoodType.valueOf(woodType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownWoodTypeException();
        }
    }
}
