package com.fabio.clinic.features.appointment;

import java.time.LocalDateTime;

public record AppointmentResponseDTO(
        Long id,
        String doctorName,
        String patientName,
        String procedureName,
        LocalDateTime start,
        LocalDateTime end
) {
    public AppointmentResponseDTO(Appointment a) {
        this(a.getId(), a.getDoctor().getName(), a.getPatient().getName(),
                a.getProcedure().getName(), a.getDateTime(), a.getEndDateTime());
    }
}