package com.fabio.clinic.features.user;

public enum UserRole {
    ADMIN("admin"),
    STAFF("staff"),
    PATIENT("patient");

    private final String role;

    UserRole(String role){
        this.role = role;
    }
    public String getRole(){
        return role;
    }
}
