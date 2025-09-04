package it.korea.app_boot.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.korea.app_boot.board.entity.BoardEntity;

// 어노테이션 생략 가능함
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{

    
}
