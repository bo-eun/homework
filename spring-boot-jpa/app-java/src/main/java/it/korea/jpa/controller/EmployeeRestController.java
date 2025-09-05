package it.korea.jpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.korea.jpa.dto.comp.EmployeeDTO;
import it.korea.jpa.service.comp.EmployeeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emp")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping("/list")
    public List<EmployeeDTO> getList() {
        return employeeService.getEmployeeList();
    }
}
