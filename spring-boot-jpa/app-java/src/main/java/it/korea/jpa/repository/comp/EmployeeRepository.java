package it.korea.jpa.repository.comp;

import org.springframework.data.jpa.repository.JpaRepository;

import it.korea.jpa.entity.comp.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

}
