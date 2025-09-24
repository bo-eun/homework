package it.korea.app_boot.board.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.korea.app_boot.board.entity.BoardEntity;

// 어노테이션 생략 가능함
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>, JpaSpecificationExecutor<BoardEntity>{

    // title 검색
    // findBy -> where 조건, Containing like %title% 로 검색
    Page<BoardEntity> findByTitleContaining(String title, Pageable pageable);

    // 글쓴이 검색
    Page<BoardEntity> findByWriterContaining(String writer, Pageable pageable);

    // data가 여러개일 경우 Query문 사용
    // :brdId는 파라미터 값
    @Query(value = """
            select b from BoardEntity b left join b.fileList where b.brdId = :brdId
            """)
    Optional<BoardEntity> getBoard(@Param("brdId") int brdId);
    
}
