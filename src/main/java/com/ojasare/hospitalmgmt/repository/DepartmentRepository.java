package com.ojasare.hospitalmgmt.repository;

import com.ojasare.hospitalmgmt.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
