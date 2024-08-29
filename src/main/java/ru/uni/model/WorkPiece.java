package ru.uni.model;

import ru.uni.enums.Diameter;
import ru.uni.enums.WoodType;

import java.util.Optional;

public interface WorkPiece {

    int getLength();

    Optional<Diameter> getDiameter();

    WoodType woodType();

}
