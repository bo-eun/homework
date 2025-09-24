package it.korea.jpa.repository.comp;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.korea.jpa.entity.comp.EmployeeEntity;
import it.korea.jpa.entity.comp.EmployeeProjection;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

    // Fetch Join
    // employee,department와 card 테이블 조인
    @Query("select e from EmployeeEntity e join fetch e.department join fetch e.card")
    List<EmployeeEntity> gEmployeeList();

    // Graph Join
    // 기존 repository에서 제공하는 함수에만 가능
    @EntityGraph(attributePaths = {"department", "card"})
    Page<EmployeeEntity> findAll(Pageable pageable);

    // JPQL 문법 중 native SQL 사용
    // native SQL는 dto를 못씀, 그래서 데이터 담을 객체나 배열 만들어 dto에 값 전달
    @Query(value = """
            select e.em_id,
                    e.em_name,
                    d1.dept_name,
                    c1.balance
            from employee e
            join emp_cards c1 on e.em_id = c1.em_id
            join deptment d1 on e.dept_id = d1.dept_id
            """,
        countQuery = "select count(*) from employee",
        nativeQuery = true) // native query를 쓰겠다
    Page<EmployeeProjection> getEmployeeAllListPage(Pageable pageable);
}
