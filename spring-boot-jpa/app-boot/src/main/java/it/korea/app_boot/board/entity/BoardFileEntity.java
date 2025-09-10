package it.korea.app_boot.board.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="board_files")
@Getter
@Setter
public class BoardFileEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int bfId;

    private String fileName;
    private String StoredName;
    private String filePath;
    private Long fileSize;

    // DB에 default value를 설정했지만 entity에서 값이 없을 경우 DB에 null이 들어갈 수 있음
    // 이 문제를 예방하기 위해 Auditing 기능 사용
    @Column(updatable = false) // 업데이트 막음
    private LocalDateTime createDate;

    // 게시글 레이지 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brd_id")
    private BoardEntity board;
}
