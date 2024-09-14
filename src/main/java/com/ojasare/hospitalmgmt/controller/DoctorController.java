package com.ojasare.hospitalmgmt.controller;

import com.ojasare.hospitalmgmt.dto.DoctorDTO;
import com.ojasare.hospitalmgmt.entity.Doctor;
import com.ojasare.hospitalmgmt.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<Doctor> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        return new ResponseEntity<>(employeeService.createDoctor(doctorDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Doctor>> getDoctorById(@PathVariable Long id) {
        Optional<Doctor> doctorDTO = employeeService.getDoctorById(id);
        return ResponseEntity.ok(doctorDTO);
    }

    @GetMapping("/speciality")
    public ResponseEntity<List<Doctor>> getDoctorsBySpeciality(@RequestParam String speciality) {
        return ResponseEntity.ok(employeeService.findDoctorsBySpeciality(speciality));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Doctor>> updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO) {
        Optional<Doctor> updatedDoctor = employeeService.updateDoctor(id, doctorDTO);
        return ResponseEntity.ok(updatedDoctor);
    }

}
