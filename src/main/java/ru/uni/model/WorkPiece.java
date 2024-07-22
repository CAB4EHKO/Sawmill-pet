package ru.uni.model;

import ru.uni.enums.Diameter;
import ru.uni.enums.WoodType;

public interface WorkPiece {

    int getLength();

    Diameter getDiameter();

    WoodType woodType();

}
