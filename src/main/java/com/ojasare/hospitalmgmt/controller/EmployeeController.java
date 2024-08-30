package com.ojasare.hospitalmgmt.controller;

import com.ojasare.hospitalmgmt.dto.EmployeeProjection;
import com.ojasare.hospitalmgmt.entity.Employee;
import com.ojasare.hospitalmgmt.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<Page<EmployeeProjection>> getAllEmployees(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "5") int size) {
        Page<EmployeeProjection> employees = employeeService.getAllEmployees(page, size);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/all-specification")
    public ResponseEntity<List<Employee>> getEmployees(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String surname) {

        List<Employee> employees = employeeService.findEmployees(firstName, surname);

        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/address")
    public ResponseEntity<List<Employee>> getEmployeesByAddress(@RequestParam String location) {
        List<Employee> employees = employeeService.getEmployeesByAddress(location);
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }
}
