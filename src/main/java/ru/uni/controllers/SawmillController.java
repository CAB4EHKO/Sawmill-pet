package ru.uni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.uni.dto.WorkPieceBatchRequest;
import ru.uni.entity.SawmillResultEntity;
import ru.uni.repositories.SawmillResultRepository;
import ru.uni.service.SawmillProcessor;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sawmill")
public class SawmillController {

    private final SawmillProcessor sawmillProcessor;
    private final SawmillResultRepository sawmillResultRepository;


    @Autowired
    public SawmillController(SawmillProcessor sawmillProcessor, SawmillResultRepository sawmillResultRepository) {
        this.sawmillProcessor = sawmillProcessor;
        this.sawmillResultRepository = sawmillResultRepository;
    }

    @PostMapping("/process")
    public ResponseEntity<Map<String, AtomicInteger>> processSawmill(@RequestBody WorkPieceBatchRequest request) {

        Map<String, AtomicInteger> result = sawmillProcessor.saw(request.getWorkPieces());

        String resultAsString = result.entrySet().stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .collect(Collectors.joining(", "));

        SawmillResultEntity resultEntity = new SawmillResultEntity();
        resultEntity.setBatchNumber(request.getBatchNumber());
        resultEntity.setResult(resultAsString);
        sawmillResultRepository.save(resultEntity);

        return ResponseEntity.ok(result);
    }
}
