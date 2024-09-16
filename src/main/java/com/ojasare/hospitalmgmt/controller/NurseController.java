package com.ojasare.hospitalmgmt.controller;

import com.ojasare.hospitalmgmt.dto.NurseDTO;
import com.ojasare.hospitalmgmt.entity.Nurse;
import com.ojasare.hospitalmgmt.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/nurse")
@RequiredArgsConstructor
public class NurseController {


    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<Nurse> createNurse(@RequestBody NurseDTO nurseDTO) {
        Nurse createdNurse = employeeService.createNurse(nurseDTO);
        return ResponseEntity.ok(createdNurse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nurse> getNurseById(@PathVariable Long id) {
        Nurse nurse = employeeService.getNurseById(id);
        return ResponseEntity.ok(nurse);
    }

    // Update a Nurse
    @PutMapping("/update/{id}")
    public ResponseEntity<Nurse> updateNurse(@PathVariable Long id, @RequestBody NurseDTO nurseDTO) {
        Nurse updatedNurse = employeeService.updateNurse(id, nurseDTO);
        return ResponseEntity.ok(updatedNurse);
    }

}