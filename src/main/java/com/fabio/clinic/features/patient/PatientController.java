package com.fabio.clinic.features.patient;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/patients")
@CrossOrigin(origins = "*")
public class PatientController {
    private final PatientService service;
    public PatientController(PatientService patientService){this.service = patientService;}

    @PostMapping
    public ResponseEntity<PatientResponseDTO> create(@Valid @RequestBody PatientRequestDTO requestDTO){
        return ResponseEntity.ok(service.create(requestDTO));
    }
}
