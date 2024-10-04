package ru.uni.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.uni.dto.SawResult;
import ru.uni.dto.SawmillRequest;
import ru.uni.entity.SawmillResultEntity;
import ru.uni.entity.SawmillResultItemEntity;
import ru.uni.model.Tree;
import ru.uni.model.WorkPiece;
import ru.uni.repositories.SawmillResultEntityRepository;
import ru.uni.service.SawmillProcessor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sawmill")
@Slf4j
public class SawmillController {

    private final SawmillProcessor sawmillProcessor;
    private final SawmillResultEntityRepository resultRepository;


    @Autowired
    public SawmillController(SawmillProcessor sawmillProcessor, SawmillResultEntityRepository resultRepository) {
        this.sawmillProcessor = sawmillProcessor;
        this.resultRepository = resultRepository;
    }

    @PostMapping("/process")
    public ResponseEntity<Map<String, Integer>> processSawmill(@RequestBody SawmillRequest request) {
        List<WorkPiece> workPieces = request.getWorkPieces().stream()
                .map(dto -> new Tree(dto.getLength(), dto.getDiameter(), dto.getWoodType()))
                .collect(Collectors.toList());

        SawResult result = sawmillProcessor.saw(workPieces);

        Map<String, AtomicInteger> boardCounts = result.getBoardCounts();

        if (!result.getFailedWorkPieces().isEmpty()) {
            log.warn("Обнаружены неизвестные типы древесины: {}", result.getFailedWorkPieces());
        }

        Map<String, Integer> resultMap = boardCounts.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get()));

        SawmillResultEntity resultEntity = new SawmillResultEntity();
        resultEntity.setBatchNumber(request.getBatchNumber());

        List<SawmillResultItemEntity> items = resultMap.entrySet().stream()
                .map(e -> {
                    SawmillResultItemEntity item = new SawmillResultItemEntity();
                    item.setWoodType(e.getKey());
                    item.setBoardCount(e.getValue());
                    item.setResult(resultEntity);
                    return item;
                })
                .collect(Collectors.toList());

        resultEntity.setItems(items);

        resultRepository.save(resultEntity);

        return ResponseEntity.ok(resultMap);
    }
}
