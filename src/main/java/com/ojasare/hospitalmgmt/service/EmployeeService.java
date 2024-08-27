package com.ojasare.hospitalmgmt.service;

import com.ojasare.hospitalmgmt.dto.DoctorDTO;
import com.ojasare.hospitalmgmt.dto.NurseDTO;
import com.ojasare.hospitalmgmt.entity.Doctor;
import com.ojasare.hospitalmgmt.entity.Nurse;

import java.util.List;

public interface EmployeeService {
    Doctor createDoctor(DoctorDTO doctorDTO);
    Doctor updateDoctor(Long doctorId, DoctorDTO doctorDTO);
    List<Doctor> findDoctorsBySpeciality(String speciality);
    Nurse createNurse(NurseDTO nurseDTO);
    Nurse updateNurse(Long nurseId, NurseDTO nurseDTO);
    Doctor getDoctorById(Long id);
    Nurse getNurseById(Long id);
}
