package com.example.curahub.controller;

import com.example.curahub.model.Patient;
import com.example.curahub.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Patient> signup(@RequestBody Patient request) {
        Patient created = patientService.signup(request);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable("id") UUID id) {
        Optional<Patient> found = patientService.getById(id);
        return found.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Patient>> listAll() {
        return ResponseEntity.ok(patientService.listAll());
    }
}


