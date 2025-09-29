package it.exam.backoffice.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.exam.backoffice.board.entity.BoardFileEntity;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Integer> {

}
