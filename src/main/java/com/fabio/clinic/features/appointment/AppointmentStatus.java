package com.fabio.clinic.features.appointment;

public enum AppointmentStatus {
    SCHEDULED("scheduled"),
    CANCELED("canceled"),
    CONFIRMED("confirmed"),
    FINISHED("finished");

    private final String status;

    AppointmentStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return status;
    }
}
