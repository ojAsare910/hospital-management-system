package com.ojasare.hospitalmgmt.service.impl;

import com.ojasare.hospitalmgmt.config.CustomTransactional;
import com.ojasare.hospitalmgmt.dto.DoctorDTO;
import com.ojasare.hospitalmgmt.dto.EmployeeProjection;
import com.ojasare.hospitalmgmt.dto.NurseDTO;
import com.ojasare.hospitalmgmt.entity.Doctor;
import com.ojasare.hospitalmgmt.entity.Employee;
import com.ojasare.hospitalmgmt.entity.Nurse;
import com.ojasare.hospitalmgmt.repository.DoctorRepository;
import com.ojasare.hospitalmgmt.repository.EmployeeRepository;
import com.ojasare.hospitalmgmt.repository.NurseRepository;
import com.ojasare.hospitalmgmt.service.DepartmentService;
import com.ojasare.hospitalmgmt.service.EmployeeService;
import com.ojasare.hospitalmgmt.specification.EmployeeSpecification;
import jakarta.persistence.QueryHint;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import static org.hibernate.jpa.QueryHints.HINT_READONLY;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;
    private final DepartmentService departmentService;
    private final EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public Doctor createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = Doctor.builder()
                .firstName(doctorDTO.getFirstName())
                .surname(doctorDTO.getSurname())
                .address(doctorDTO.getAddress())
                .telephoneNumber(doctorDTO.getTelephoneNumber())
                .speciality(doctorDTO.getSpeciality())
                .build();
        Doctor savedDoctor = doctorRepository.save(doctor);
        logger.info("Doctor created successfully");
        return savedDoctor;
    }

    @CustomTransactional
    @CacheEvict(value = "doctorsBySpeciality", allEntries = true)
    @Override
    public Optional<Doctor> updateDoctor(Long doctorId, DoctorDTO doctorDTO) {
        return doctorRepository.findById(doctorId).map(doctor -> {
            doctor.setFirstName(doctorDTO.getFirstName());
            doctor.setSurname(doctorDTO.getSurname());
            doctor.setAddress(doctorDTO.getAddress());
            doctor.setTelephoneNumber(doctorDTO.getTelephoneNumber());
            doctor.setSpeciality(doctorDTO.getSpeciality());
            Doctor updatedDoctor = doctorRepository.save(doctor);
            logger.info("Doctor updated successfully");
            return updatedDoctor;
        });
    }

    @Cacheable(value = "doctorsBySpeciality", key = "#speciality", unless = "#result.isEmpty()")
    @Transactional(readOnly = true)
    @Override
    public List<Doctor> findDoctorsBySpeciality(String speciality) {
        logger.info("Finding doctors by speciality: {}", speciality);
        List<Doctor> doctors = doctorRepository.findBySpeciality(speciality);
        logger.info("Found {} doctors with speciality: {}", doctors.size(), speciality);
        return doctors;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Doctor> getDoctorById(Long doctorId) {
        logger.debug("Fetching doctor with id: {}", doctorId);
        return doctorRepository.findById(doctorId);
    }

    @Transactional
    @Override
    public Nurse createNurse(NurseDTO nurseDTO) {
        Nurse nurse = Nurse.builder()
                .firstName(nurseDTO.getFirstName())
                .surname(nurseDTO.getSurname())
                .address(nurseDTO.getAddress())
                .telephoneNumber(nurseDTO.getTelephoneNumber())
                .department(departmentService.getDepartmentById(nurseDTO.getDepartmentId()))
                .salary(nurseDTO.getSalary())
                .rotation(nurseDTO.getRotation())
                .build();
        Nurse savedNurse = nurseRepository.save(nurse);
        logger.info("Nurse created successfully");
        return savedNurse;
    }

    @CustomTransactional
    @CacheEvict(value = "nurses", allEntries = true)
    @Override
    public Optional<Nurse> updateNurse(Long nurseId, NurseDTO nurseDTO) {
        return nurseRepository.findById(nurseId).map(nurse -> {
            nurse.setFirstName(nurseDTO.getFirstName());
            nurse.setSurname(nurseDTO.getSurname());
            nurse.setAddress(nurseDTO.getAddress());
            nurse.setTelephoneNumber(nurseDTO.getTelephoneNumber());
            nurse.setDepartment(departmentService.getDepartmentById(nurseDTO.getDepartmentId()));
            nurse.setSalary(nurseDTO.getSalary());
            nurse.setRotation(nurseDTO.getRotation());
            Nurse updatedNurse = nurseRepository.save(nurse);
            logger.info("Nurse updated successfully");
            return updatedNurse;
        });
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Nurse> getNurseById(Long nurseId) {
        logger.debug("Fetching nurse with id: {}", nurseId);
        return nurseRepository.findById(nurseId);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<EmployeeProjection> getAllEmployees(int page, int size) {
        logger.info("Fetching all employees, page: {}, size: {}", page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<EmployeeProjection> employees = employeeRepository.findAllEmployees(pageable);
        logger.info("Found {} employees", employees.getTotalElements());
        return employees;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Employee> findEmployees(String firstName, String surname) {
        List<Employee> employees = employeeRepository.findAll(Specification
                .where(EmployeeSpecification.hasFirstName(firstName))
                .or(EmployeeSpecification.hasSurname(surname)));
        logger.info("Found {} employees", employees.size());
        return employees;
    }

    @Transactional(readOnly = true)
    @QueryHints(value = @QueryHint(name = HINT_READONLY, value = "true"))
    @Override
    public List<Employee> getEmployeesByAddress(String address) {
        List<Employee> employees = employeeRepository.findByAddress(address);
        logger.info("Found {} employees with address: {}", employees.size(), address);
        return employees;
    }
}