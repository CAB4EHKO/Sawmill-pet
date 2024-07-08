package ru.uni.model;

public class Tree {

    private int diameter;
    private String woodType;
    private int length;

    public Tree(int length, String woodType, int diameter) {
        this.length = length;
        this.diameter = diameter;
        this.woodType = woodType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public String getWoodType() {
        return woodType;
    }

    public void setWoodType(String woodType) {
        this.woodType = woodType;
    }
}
