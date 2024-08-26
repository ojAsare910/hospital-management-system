package com.ojasare.hospitalmgmt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Doctor extends Employee {
    private String speciality;

    @OneToMany(mappedBy = "director")
    private Set<Department> departments;

    @OneToMany(mappedBy = "doctor")
    private Set<Patient> patients;

}
