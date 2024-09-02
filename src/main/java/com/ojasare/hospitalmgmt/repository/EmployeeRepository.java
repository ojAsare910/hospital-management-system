package com.ojasare.hospitalmgmt.repository;

import com.ojasare.hospitalmgmt.dto.EmployeeProjection;
import com.ojasare.hospitalmgmt.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    @Query(value = "SELECT e.id, e.surname, e.first_name, e.address, e.telephone_number,\n" +
            "       d.speciality, n.salary, n.rotation, d2.name as departmentName, d3.\"name\" as departmentSupervise\n" +
            "FROM employee e\n" +
            "         LEFT JOIN doctor d ON e.id = d.id\n" +
            "         LEFT JOIN nurse n ON e.id = n.id\n" +
            "         LEFT JOIN department d2 on n.department_id = d2.id\n" +
            "         left join department d3 on d.id = d3.director_id",
            nativeQuery = true)
    Page<EmployeeProjection> findAllEmployees(Pageable pageable);

    List<Employee> findByAddress(String address);

}
