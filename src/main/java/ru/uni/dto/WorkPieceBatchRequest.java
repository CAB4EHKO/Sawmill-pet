package ru.uni.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.uni.model.WorkPiece;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class WorkPieceBatchRequest {

    private List<WorkPiece> workPieces;
    private String batchNumber;

}
