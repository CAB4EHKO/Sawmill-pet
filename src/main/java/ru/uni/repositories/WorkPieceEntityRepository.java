package ru.uni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.uni.entity.WorkPieceEntity;

@Repository
public interface WorkPieceEntityRepository extends JpaRepository<WorkPieceEntity, Long> {

}
