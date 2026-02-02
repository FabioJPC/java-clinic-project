package com.fabio.clinic.features.patient;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/patients")
public class PatientController {
    private final PatientService service;
    public PatientController(PatientService patientService){this.service = patientService;}

    @PostMapping
    public Patient create(@Valid @RequestBody PatientRequestDTO requestDTO){
        return service.create(requestDTO);
    }
}
