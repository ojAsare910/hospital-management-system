package com.ojasare.hospitalmgmt.service.impl;

import com.ojasare.hospitalmgmt.dto.DepartmentDTO;
import com.ojasare.hospitalmgmt.entity.Department;
import com.ojasare.hospitalmgmt.entity.Doctor;
import com.ojasare.hospitalmgmt.repository.DepartmentRepository;
import com.ojasare.hospitalmgmt.repository.DoctorRepository;
import com.ojasare.hospitalmgmt.service.DepartmentService;
import com.ojasare.hospitalmgmt.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Invalid department Id"));
    }

    @Override
    public Department createDepartment(DepartmentDTO departmentDTO) {
        Doctor doctor = doctorRepository.findById(departmentDTO.getDirectorId()).orElseThrow(() -> new IllegalStateException("Invalid Director Id"));;
        Department department = Department.builder()
                .name(departmentDTO.getName())
                .code(departmentDTO.getCode())
                .director(doctor)
                .building(departmentDTO.getBuilding())
                .build();
        return departmentRepository.save(department);
    }


}
