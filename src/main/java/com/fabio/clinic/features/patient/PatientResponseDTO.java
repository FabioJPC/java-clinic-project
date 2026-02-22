package com.fabio.clinic.features.patient;

public record PatientResponseDTO(
        String name,
        String phone,
        String email
) {
    public PatientResponseDTO(Patient patient){
        this(patient.getName(), patient.getPhone(), patient.getEmail());
    }
}
