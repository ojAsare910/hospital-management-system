package com.ojasare.hospitalmgmt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int wardNumber;
    private int numberOfBeds;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne
    @JoinColumn(name = "supervisor_id")
    private Nurse supervisor;

    @OneToMany(mappedBy = "ward")
    private Set<Hospitalisation> hospitalisations;

}