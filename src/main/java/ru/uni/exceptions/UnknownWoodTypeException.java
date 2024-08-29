package ru.uni.exceptions;

public class UnknownWoodTypeException extends RuntimeException {
    public UnknownWoodTypeException() {
        super("Неизвестный тип древесины");
    }
}
