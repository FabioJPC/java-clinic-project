package com.fabio.clinic.features.doctor;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService service;

    public DoctorController(DoctorService doctorService){
        this.service = doctorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor create(@Valid @RequestBody DoctorRequestDTO request){
        return service.create(request);
    }

    @GetMapping
    ResponseEntity<List<Doctor>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
