package it.korea.jpa.entity.comp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="employee")
@Entity
public class EmployeeEntity {

    @Id
    private String emId;
    private String emName;

    // 부서 매핑
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="dept_id", nullable=false)
    // 내가 가지고 있는 상대방의 기본키(dept_id)와 join할 대상 테이블의 기본키가 자동으로 매핑된다.
    private DepartEntity department;

    // 양방향 매핑, 자주 쓰지 않는다.
    // 연결된 테이블 서로 영향을 끼침
    // 상대쪽 테이블에서 나를 매칭할 때 썼던 멤버객체 이름
    // mappedBy 양방향 관계에서 주인이 아닌 엔터티 쪽에서 사용하는 속성, 이 연관관계의 관리는 내가 아니라 "emp"쪽에 있어
    // orphanRemoval = true 고아삭제 true
    // optional 기본값 true, finBy 시 left join이 발생할 경우 optional을 true로 변경하면 join으로 처리된다.
    @OneToOne(mappedBy = "emp", cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private EmCardEntity card;
}
