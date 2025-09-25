package it.back.back_app.board.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.back.back_app.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer>, JpaSpecificationExecutor<BoardEntity> {

    // EntityGraph는 기본적으로 JpaRepository가 제공하는 함수를 override만 가능
    @EntityGraph(attributePaths = {"fileList"})
    Page<BoardEntity> findAll (Pageable pageable);

    // n + 1 현상을 해결하기 위해 Query 작성
    @Query( value = """
        select b from BoardEntity b left join fetch b.fileList
        where b.brdId = :brdId
        """)
    Optional<BoardEntity> getBoard(@Param("brdId") int brdId);
}
