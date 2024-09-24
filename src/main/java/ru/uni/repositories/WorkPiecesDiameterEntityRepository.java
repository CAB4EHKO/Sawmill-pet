package ru.uni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.uni.entity.WorkPiecesDiameterEntity;

@Repository
public interface WorkPiecesDiameterEntityRepository extends JpaRepository<WorkPiecesDiameterEntity, Long> {

}
