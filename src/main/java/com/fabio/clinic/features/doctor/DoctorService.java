package com.fabio.clinic.features.doctor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository repository;
    public DoctorService(DoctorRepository doctorRepository){
        this.repository = doctorRepository;
    }

    public Doctor create(DoctorRequestDTO data){
        Doctor doctor = new Doctor(data);
        return repository.save(doctor);
    }
    public List<Doctor> findAll(){
        return repository.findAll();
    }
}
