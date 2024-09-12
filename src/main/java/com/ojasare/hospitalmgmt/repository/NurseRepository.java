package com.ojasare.hospitalmgmt.repository;

import com.ojasare.hospitalmgmt.entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NurseRepository extends JpaRepository<Nurse, Long> {
    @Query("SELECT n FROM Nurse n JOIN FETCH n.department WHERE n.id = :nurseId")
    Optional<Nurse> findByIdWithDepartment(@Param("nurseId") Long nurseId);
}
