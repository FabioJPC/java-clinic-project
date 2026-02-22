package com.fabio.clinic.features.patient;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PatientRequestDTO (
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String phone,
        @NotBlank @Past LocalDate birthdate,
        @NotBlank
        @Pattern(regexp = "\\d{11}", message = "O cpf deve conter apenas os 11 dígitos")
        String cpf
        ){ }
