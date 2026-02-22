package com.fabio.clinic.features.patient;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record PatientUpdateDTO (
        String name,
        @Email
        String email,
        String phone,
        @Past
        LocalDate birthdate,
        @Pattern(regexp = "\\d{11}", message = "O cpf deve conter apenas os 11 dígitos")
        String cpf
){
}
