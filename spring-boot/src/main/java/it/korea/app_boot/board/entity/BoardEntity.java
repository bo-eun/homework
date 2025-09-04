package it.korea.app_boot.board.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity 
@Table(name="board") // 어떤 테이블하고 연결되어 있는지
public class BoardEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brdId;

    private String title;

    private String contents;

    private String writer;

    private int readCount;

    private int likeCount;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}

// toString은 넣지 않기 위해 Getter와 Setter 분리...
// entity 하나를 출력할 때는 상관없지만, 여러개를 join한 후 출력할 때에는 문제가 생긴다.(entity끼리 서로 출력을 미뤄 무한루프 오류가 생김)
// 그래서 entity를 바로 출력하지 않고 DTO를 출력한다.
// server에서 받은 객체 -> DTO(data transform object) -> entity
// entity는 CRUD할 때만 쓴다. entity자체를 도구로 쓰지 않음...(그건 DTO를 씀)