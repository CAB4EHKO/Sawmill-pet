package ru.uni.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ru.uni.enums.Diameter;
import ru.uni.enums.WoodType;

import java.util.Optional;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Tree.class, name = "tree"),
})
public interface WorkPiece {

    int getLength();

    Optional<Diameter> getDiameter();

    WoodType woodType();

}
