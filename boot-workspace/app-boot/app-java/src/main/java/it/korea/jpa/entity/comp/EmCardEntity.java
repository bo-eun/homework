package it.korea.jpa.entity.comp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="emp_cards")
@Getter
@Setter
public class EmCardEntity {

    @Id
    private String cardId;
    private int balance;

    // cascade = CascadeType.All 반대쪽 테이블에 줘서 여기는 안줘도 됨
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="em_id", nullable = false)
    EmployeeEntity emp;

}
