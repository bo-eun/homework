package it.korea.jpa.entity.comp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private String deptId;
    private String deptName;

    // 부서 매핑
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="dept_id", nullable=false)
    private DepartEntity department;
}
