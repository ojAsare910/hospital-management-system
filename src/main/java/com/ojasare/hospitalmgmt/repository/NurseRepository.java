package com.ojasare.hospitalmgmt.repository;

import com.ojasare.hospitalmgmt.entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NurseRepository extends JpaRepository<Nurse, Long> {
}
