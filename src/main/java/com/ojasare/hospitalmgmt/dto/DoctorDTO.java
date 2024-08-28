package com.ojasare.hospitalmgmt.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO extends EmployeeDTO {
    private String speciality;
}