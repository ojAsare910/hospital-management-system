package com.ojasare.hospitalmgmt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface EmployeeProjection {
    Long getId();
    String getSurname();
    String getFirstName();
    String getAddress();
    String getTelephoneNumber();
    String getSpeciality();
    Double getSalary();
    String getRotation();
    String getDepartmentName();
    String getDepartmentSupervise();
}

