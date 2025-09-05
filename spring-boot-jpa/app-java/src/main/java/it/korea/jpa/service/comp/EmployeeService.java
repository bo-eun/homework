package it.korea.jpa.service.comp;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.korea.jpa.dto.comp.EmployeeDTO;
import it.korea.jpa.entity.comp.EmployeeEntity;
import it.korea.jpa.repository.comp.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public List<EmployeeDTO> getEmployeeList() {
        List<EmployeeEntity> list = employeeRepository.findAll();
        List<EmployeeDTO> empList = list.stream().map(EmployeeDTO::of).toList();
        
        return empList;
    }
}
