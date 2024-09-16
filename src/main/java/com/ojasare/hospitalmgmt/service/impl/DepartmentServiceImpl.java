package com.ojasare.hospitalmgmt.service.impl;

import com.ojasare.hospitalmgmt.dto.DepartmentDTO;
import com.ojasare.hospitalmgmt.entity.Department;
import com.ojasare.hospitalmgmt.entity.Doctor;
import com.ojasare.hospitalmgmt.exception.NotFoundException;
import com.ojasare.hospitalmgmt.repository.DepartmentRepository;
import com.ojasare.hospitalmgmt.repository.DoctorRepository;
import com.ojasare.hospitalmgmt.service.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    @Override
    @Cacheable(value = "departments", key = "#id", cacheManager = "redisCacheManager")
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Department not found with id: " + id));
    }

    @Transactional
    @Override
    public Department createDepartment(DepartmentDTO departmentDTO) {
        Doctor doctor = doctorRepository.findById(departmentDTO.getDirectorId()).orElseThrow(() -> new EntityNotFoundException("Invalid Director Id"));;
        Department department = Department.builder()
                .name(departmentDTO.getName())
                .code(departmentDTO.getCode())
                .director(doctor)
                .building(departmentDTO.getBuilding())
                .build();
        return departmentRepository.save(department);
    }
}
