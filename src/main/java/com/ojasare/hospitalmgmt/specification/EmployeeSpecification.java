package com.ojasare.hospitalmgmt.specification;

import com.ojasare.hospitalmgmt.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

    public static Specification<Employee> hasFirstName(String firstName) {
        return (root, query, cb) ->
             cb.equal(root.get("firstName"), firstName);
    }

    public static Specification<Employee> hasSurname(String surname) {
        return (root, query, cb) ->
                cb.equal(root.get("surname"), surname);
    }
}
