package com.fabio.clinic.features.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank String name,
        @NotBlank @Size(min = 8) String password,
        UserRole role
) {}
