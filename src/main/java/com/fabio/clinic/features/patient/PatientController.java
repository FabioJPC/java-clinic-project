package com.fabio.clinic.features.patient;

import com.fabio.clinic.features.procedure.ProcedureResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/patients")
@CrossOrigin(origins = "*")
public class PatientController {
    private final PatientService service;
    public PatientController(PatientService patientService){this.service = patientService;}

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>>listAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> create(@Valid @RequestBody PatientRequestDTO requestDTO){
        return ResponseEntity.ok(service.create(requestDTO));
    }

}
