package com.fabio.clinic.features.patient;

import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository repository;

    public PatientService(PatientRepository patientRepository){this.repository = patientRepository;}

    public Patient create(PatientRequestDTO data){
        if (repository.existsByEmail(data.email())) {
            throw new RuntimeException("Este e-mail já está cadastrado!");
        }
        Patient patient = new Patient(data);
        return repository.save(patient);
    }
}
