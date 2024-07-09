package ru.uni.model;

import ru.uni.enums.Diameter;
import ru.uni.enums.WoodType;
import ru.uni.exceptions.UnknownWoodTypeException;

public class Tree {

    private int length;
    private Diameter diameter;
    private WoodType woodType;

    public Tree(int length, int diameter, Object woodType) {
        this.length = length;
        this.diameter = Diameter.fromDiameter(diameter);
        if (this.diameter == null) {
            System.out.println("Загатовку диаметра: " + diameter + " невозможно обработать");
        }
        if (woodType instanceof WoodType) {
            this.woodType = (WoodType) woodType;
        } else {
            try {
                this.woodType = WoodType.valueOf(woodType.toString().toUpperCase());
            } catch (IllegalArgumentException e) {
                this.woodType = null;
            }
        }
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDiameter() {
        return diameter.getDiameter();
    }

    public void setDiameter(int diameter) {
        this.diameter = Diameter.fromDiameter(diameter);
    }

    public WoodType getWoodType() throws UnknownWoodTypeException {
        if (woodType == null) {
            throw new UnknownWoodTypeException("Unknown wood type");
        }
        return woodType;
    }

    public void setWoodType(WoodType woodType) throws UnknownWoodTypeException {
        if (woodType == null) {
            throw new UnknownWoodTypeException("Unknown wood type");
        }
        this.woodType = woodType;
    }

    public int getBoardsPerTwoMeters() throws UnknownWoodTypeException {
        if (diameter == null) {
            throw new UnknownWoodTypeException("Unknown diameter");
        }
        return diameter.getBoardsPerTwoMeters();
    }
}
