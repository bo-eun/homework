package it.korea.app_boot.board.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import it.korea.app_boot.common.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity 
@Table(name="board") // 어떤 테이블하고 연결되어 있는지
public class BoardEntity extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brdId;

    private String title;

    private String contents;

    private String writer;

    private int readCount;

    private int likeCount;
    
/*  BaseEntity를 상속받았기 때문에(상속관계는 아님) 
    BaseEntity에 있는 아래 속성 필요 없음
    private LocalDateTime createDate;

    private LocalDateTime updateDate;
 */

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true/* , fetch=FetchType.LAZY 기본값이므로 생략가능*/)
    // 한 번 셀렉트하고 셀렉트한 결과를 서브쿼리에 넣어 sql문 작성
    // 하이버네이트 전용. 데이터 적을 때만 사용하기
    // @Fetch(FetchMode.SUBSELECT) 
    private Set<BoardFileEntity> fileList = new HashSet<>();

    public void addFiles(BoardFileEntity entity) {
        if(fileList == null) this.fileList = new HashSet<>();
        entity.setBoard(this);
        fileList.add(entity);
    }

}

// toString은 넣지 않기 위해 Getter와 Setter 분리...
// entity 하나를 출력할 때는 상관없지만, 여러개를 join한 후 출력할 때에는 문제가 생긴다.(entity끼리 서로 출력을 미뤄 무한루프 오류가 생김)
// 그래서 entity를 바로 출력하지 않고 DTO를 출력한다.
// server에서 받은 객체 -> DTO(data transform object) -> entity
// entity는 CRUD할 때만 쓴다. entity자체를 도구로 쓰지 않음...(그건 DTO를 씀)