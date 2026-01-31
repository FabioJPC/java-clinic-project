package com.fabio.clinic.features.doctor;

import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final DoctorRepository repository;
    public DoctorService(DoctorRepository doctorRepository){
        this.repository = doctorRepository;
    }
}
