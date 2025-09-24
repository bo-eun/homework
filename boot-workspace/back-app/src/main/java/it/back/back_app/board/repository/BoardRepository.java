package it.back.back_app.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.back.back_app.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer>, JpaSpecificationExecutor<BoardEntity> {

    // EntityGraph는 기본적으로 JpaRepository가 제공하는 함수를 override만 가능
    @EntityGraph(attributePaths = {"fileList"})
    Page<BoardEntity> findAll (Pageable pageable);
}
