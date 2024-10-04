package ru.uni.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.uni.enums.FileStatus;

@Entity
@Table(name = "file_processing_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileProcessingStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Enumerated(EnumType.STRING)
    private FileStatus status;

    @Column(columnDefinition = "TEXT")
    private String comment;
}
