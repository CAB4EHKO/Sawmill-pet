package ru.uni.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SawmillRequest {

    private String batchNumber;
    private List<WorkPieceDto> workPieces;

}
