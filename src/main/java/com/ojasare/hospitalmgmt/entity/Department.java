package com.ojasare.hospitalmgmt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private String building;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Doctor director;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private Set<Ward> wards;
}

