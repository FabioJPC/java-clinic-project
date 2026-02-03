package com.fabio.clinic.features.user;

import jakarta.validation.constraints.Size;

public record UserUpdateDTO(
        String name,
        @Size(min = 8) String password,
        @Size(min = 8) String confirmPassword,
        UserRole role
) {
}
