package it.back.back_app.board.entity;

import java.util.HashSet;
import java.util.Set;

import it.back.back_app.common.domain.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true/* , fetch=FetchType.LAZY 기본값이므로 생략가능*/)
    // 한 번 셀렉트하고 셀렉트한 결과를 서브쿼리에 넣어 sql문 작성
    // 하이버네이트 전용. 데이터 적을 때만 사용하기
    // @Fetch(FetchMode.SUBSELECT) 
    private Set<ReviewFileEntity> fileList = new HashSet<>();

    public void addFiles(ReviewFileEntity entity) {
        if(fileList == null) this.fileList = new HashSet<>();
        entity.setReview(this);
        fileList.add(entity);
    }

}
