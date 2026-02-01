package com.fabio.clinic.features.doctor;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService service;

    public DoctorController(DoctorService doctorService){
        this.service = doctorService;
    }

    @GetMapping
    public Doctor create(@Valid DoctorRequestDTO request){

    }
}
