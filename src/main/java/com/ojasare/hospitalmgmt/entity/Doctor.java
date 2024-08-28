package com.ojasare.hospitalmgmt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Doctor extends Employee {
    private String speciality;

    @JsonIgnore
    @OneToMany(mappedBy = "director")
    private Set<Department> departments;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private Set<Patient> patients;

}
