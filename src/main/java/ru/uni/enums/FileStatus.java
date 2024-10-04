package ru.uni.enums;

import lombok.Getter;

@Getter
public enum FileStatus {
    ACCEPTED_FOR_PROCESSING("Принят в обработку"),
    PROCESSED("Обработан"),
    PARTIALLY_PROCESSED("Обработан частично"),
    FAILED("Ошибка обработки");

    private final String description;

    FileStatus(String description) {
        this.description = description;
    }
}
