package ru.uni.exceptions;

public class UnknownWoodTypeException extends RuntimeException {
    public UnknownWoodTypeException(String message) {
        super(message);
    }
}
