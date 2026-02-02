package com.fabio.clinic.features.doctor;

import jakarta.validation.constraints.NotBlank;

public record DoctorRequestDTO (
        @NotBlank String name,
        @NotBlank String speciality,
        @NotBlank String registration
){}


