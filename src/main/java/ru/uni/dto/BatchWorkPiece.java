package ru.uni.dto;


import java.util.List;

public class BatchWorkPiece {

    private String batchNumber;
    private List<WorkPieceDto> workPieces;

    public BatchWorkPiece(String batchNumber, List<WorkPieceDto> workPieces) {
        this.batchNumber = batchNumber;
        this.workPieces = workPieces;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public List<WorkPieceDto> getWorkPieces() {
        return workPieces;
    }
}
