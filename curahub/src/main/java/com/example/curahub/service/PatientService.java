package com.example.curahub.service;

import com.example.curahub.model.Patient;
import com.example.curahub.repository.PatientRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient signup(Patient incoming) {
        Patient toSave = new Patient(null, incoming.getFirstName(), incoming.getLastName(), incoming.getEmail(), incoming.getPhone());
        try {
            return patientRepository.save(toSave);
        } catch (DataIntegrityViolationException e) {
            throw e;
        }
    }

    public Optional<Patient> getById(UUID id) {
        return patientRepository.findById(id);
    }

    public List<Patient> listAll() {
        return patientRepository.findAll();
    }
}


