package com.fabio.clinic.features.patient;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PatientRequestDTO (
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String phone
){ }
