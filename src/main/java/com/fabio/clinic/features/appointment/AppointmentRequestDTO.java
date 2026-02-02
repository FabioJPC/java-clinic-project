package com.fabio.clinic.features.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentRequestDTO(
        @NotNull
        Long doctor_id,
        @NotNull
        Long patient_id,
        @Future
        LocalDateTime dateTime,
        @NotNull
        Long procedure_id
) {
}
