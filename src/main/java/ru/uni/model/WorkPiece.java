package ru.uni.model;

import ru.uni.enums.Diameter;
import ru.uni.enums.WoodType;
import ru.uni.exceptions.UnknownWoodTypeException;

import java.util.Optional;


public interface WorkPiece {

    int getLength();

    Optional<Diameter> getDiameter();

    WoodType woodType() throws UnknownWoodTypeException;

}
