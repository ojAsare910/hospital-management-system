package com.ojasare.hospitalmgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NurseDTO extends EmployeeDTO {
    private double salary;
    private String rotation;
    private Long departmentId;
}