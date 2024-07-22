package ru.uni.model;

import ru.uni.enums.Diameter;
import ru.uni.enums.WoodType;
import ru.uni.exceptions.UnknownWoodTypeException;

import static ru.uni.enums.Diameter.*;
import static ru.uni.enums.WoodType.OAK;

public class Tree implements WorkPiece {

    private int length;
    private int diameter;
    private String woodType;

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
    public Diameter getDiameter() {
        if (SMALL.getDiameter() == diameter) {
            return SMALL;
        } else if (MEDIUM.getDiameter() == diameter) {
            return MEDIUM;
        } else if (LARGE.getDiameter() == diameter) {
            return LARGE;
        }

        throw new IllegalArgumentException("Diameter not recognized");
    }

    @Override
    public WoodType woodType() {
        return WoodType.valueOf(woodType.toUpperCase());
    }


}
