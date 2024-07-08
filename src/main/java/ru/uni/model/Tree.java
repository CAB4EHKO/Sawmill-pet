package ru.uni.model;

import ru.uni.enums.Diameter;
import ru.uni.enums.WoodType;

public class Tree {

    private int length;
    private Diameter diameter;
    private WoodType woodType;

    public Tree(int length, int diameter, WoodType woodType) {
        this.length = length;
        this.diameter = Diameter.fromDiameter(diameter);
        this.woodType = woodType;
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

    public WoodType getWoodType() {
        return woodType;
    }

    public void setWoodType(WoodType woodType) {
        this.woodType = woodType;
    }

    public int getBoardsPerTwoMeters() {
        return diameter.getBoardsPerTwoMeters();
    }
}
