package ru.uni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.uni.entity.SawmillResultEntity;

@Repository
public interface SawmillResultEntityRepository extends JpaRepository<SawmillResultEntity, Long> {
}
