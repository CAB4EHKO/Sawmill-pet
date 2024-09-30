package ru.uni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.uni.entity.SawmillResultEntity;

public interface SawmillResultRepository extends JpaRepository<SawmillResultEntity, Long> {
}
