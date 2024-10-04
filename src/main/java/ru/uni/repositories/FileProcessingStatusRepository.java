package ru.uni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.uni.entity.FileProcessingStatus;

import java.util.Optional;

@Repository
public interface FileProcessingStatusRepository extends JpaRepository<FileProcessingStatus, Long> {
    Optional<FileProcessingStatus> findByFileName(String fileName);
}
