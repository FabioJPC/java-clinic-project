package com.fabio.clinic.features.user;

public record UserResponseDTO(
        Long id,
        String name,
        UserRole role
) {
    public UserResponseDTO(User user){
        this(user.getId(),user.getName(), user.getRole());
    }


}
