package com.ojasare.hospitalmgmt.repository;

import com.ojasare.hospitalmgmt.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query(value = "SELECT * FROM employee e LEFT JOIN doctor d ON e.id = d.id WHERE d.speciality = :speciality", nativeQuery = true)
    List<Doctor> findBySpeciality(@Param("speciality") String speciality);

    Optional<Doctor> findById(Long id);
}
