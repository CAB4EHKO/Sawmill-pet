package ru.uni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.uni.entity.BoardEntity;

@Repository
public interface BoardEntityRepository extends JpaRepository<BoardEntity, Long> {

}
