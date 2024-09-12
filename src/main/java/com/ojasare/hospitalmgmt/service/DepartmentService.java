package com.ojasare.hospitalmgmt.service;

import com.ojasare.hospitalmgmt.dto.DepartmentDTO;
import com.ojasare.hospitalmgmt.entity.Department;

import java.util.Optional;

public interface DepartmentService {
    Department getDepartmentById(Long id);
    Department createDepartment(DepartmentDTO departmentDTO);
}
