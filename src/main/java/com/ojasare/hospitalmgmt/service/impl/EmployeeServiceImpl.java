package com.ojasare.hospitalmgmt.service.impl;

import com.ojasare.hospitalmgmt.dto.DoctorDTO;
import com.ojasare.hospitalmgmt.dto.NurseDTO;
import com.ojasare.hospitalmgmt.entity.Department;
import com.ojasare.hospitalmgmt.entity.Doctor;
import com.ojasare.hospitalmgmt.entity.Nurse;
import com.ojasare.hospitalmgmt.repository.DoctorRepository;
import com.ojasare.hospitalmgmt.repository.NurseRepository;
import com.ojasare.hospitalmgmt.service.DepartmentService;
import com.ojasare.hospitalmgmt.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;
    private final DepartmentService departmentService;

    @Override
    public Doctor createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = Doctor.builder()
                .firstName(doctorDTO.getFirstName())
                .surname(doctorDTO.getSurname())
                .address(doctorDTO.getAddress())
                .telephoneNumber(doctorDTO.getTelephoneNumber())
                .speciality(doctorDTO.getSpeciality())
                .build();
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Long doctorId, DoctorDTO doctorDTO) {
        return doctorRepository.findById(doctorId).map(doctor -> {
            doctor.setFirstName(doctorDTO.getFirstName());
            doctor.setSurname(doctorDTO.getSurname());
            doctor.setAddress(doctorDTO.getAddress());
            doctor.setTelephoneNumber(doctorDTO.getTelephoneNumber());
            doctor.setSpeciality(doctorDTO.getSpeciality());
            return doctorRepository.save(doctor);
        }).orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
    }

    @Override
    public List<Doctor> findDoctorsBySpeciality(String speciality) {
        return doctorRepository.findBySpeciality(speciality);
    }


    @Override
    public Nurse createNurse(NurseDTO nurseDTO) {
        Department department = departmentService.getDepartmentById(nurseDTO.getDepartmentId());
        Nurse nurse = Nurse.builder()
                .firstName(nurseDTO.getFirstName())
                .surname(nurseDTO.getSurname())
                .address(nurseDTO.getAddress())
                .telephoneNumber(nurseDTO.getTelephoneNumber())
                .department(department)
                .salary(nurseDTO.getSalary())
                .rotation(nurseDTO.getRotation())
                .build();
        return nurseRepository.save(nurse);
    }

    @Override
    public Nurse updateNurse(Long nurseId, NurseDTO nurseDTO) {
        return nurseRepository.findById(nurseId).map(nurse -> {
            nurse.setFirstName(nurseDTO.getFirstName());
            nurse.setSurname(nurseDTO.getSurname());
            nurse.setAddress(nurseDTO.getAddress());
            nurse.setTelephoneNumber(nurseDTO.getTelephoneNumber());
            nurse.setDepartment(departmentService.getDepartmentById(nurseDTO.getDepartmentId()));
            nurse.setSalary(nurseDTO.getSalary());
            nurse.setRotation(nurseDTO.getRotation());
            return nurseRepository.save(nurse);
        }).orElseThrow(() -> new EntityNotFoundException("Nurse not found"));
    }

    @Override
    public Doctor getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
    }

    @Override
    public Nurse getNurseById(Long nurseId) {
        return nurseRepository.findById(nurseId)
                .orElseThrow(() -> new EntityNotFoundException("Nurse not found"));
    }


}
