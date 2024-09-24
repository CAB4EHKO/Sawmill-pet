package ru.uni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.uni.entity.WoodTypeEntity;

@Repository
public interface WoodTypeEntityRepository extends JpaRepository<WoodTypeEntity, Long> {

}
