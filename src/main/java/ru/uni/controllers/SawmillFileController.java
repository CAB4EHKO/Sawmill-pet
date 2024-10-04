package ru.uni.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.uni.entity.FileProcessingStatus;
import ru.uni.enums.FileStatus;
import ru.uni.repositories.FileProcessingStatusRepository;
import ru.uni.service.FileProcessingService;

import java.util.Optional;

@RestController
@RequestMapping("/sawmill")
public class SawmillFileController {

    private final FileProcessingService fileProcessingService;
    private final FileProcessingStatusRepository statusRepository;

    public SawmillFileController(FileProcessingService fileProcessingService,
                                 FileProcessingStatusRepository statusRepository) {
        this.fileProcessingService = fileProcessingService;
        this.statusRepository = statusRepository;
    }

    @Operation(summary = "Загрузить CSV файл с заготовками", description = "Принимает CSV файл с данными по заготовкам и номерами партий")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileProcessingStatus> uploadFile(
            @Parameter(description = "CSV файл с данными по заготовкам", required = true,
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
            @RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        String fileName = file.getOriginalFilename();

        FileProcessingStatus status = new FileProcessingStatus();
        status.setFileName(fileName);
        status.setStatus(FileStatus.ACCEPTED_FOR_PROCESSING);

        statusRepository.save(status);

        fileProcessingService.processFileAsync(file, status);

        return ResponseEntity.ok(status);
    }

    @Operation(summary = "Получить статус обработки файла", description = "Возвращает текущий статус обработки загруженного файла по имени")
    @GetMapping("/status")
    public ResponseEntity<FileProcessingStatus> getFileStatus(
            @Parameter(description = "Имя загруженного файла", required = true)
            @RequestParam("fileName") String fileName) {
        Optional<FileProcessingStatus> status = statusRepository.findByFileName(fileName);
        return status.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
