package ru.uni.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.uni.model.WorkPiece;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SawResult {

    private Map<String, AtomicInteger> boardCounts;
    private List<WorkPiece> failedWorkPieces;

}
