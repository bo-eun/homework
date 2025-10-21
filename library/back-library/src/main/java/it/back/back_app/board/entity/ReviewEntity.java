package it.back.back_app.board.entity;

import it.back.back_app.common.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity 
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="review") // 어떤 테이블하고 연결되어 있는지
public class ReviewEntity extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    private int bookId;

    private String userId;

    @Column(columnDefinition = "TINYINT")
    private Byte rating;

    @Column(columnDefinition = "TEXT")
    private String content;
}
