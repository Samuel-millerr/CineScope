package com.project.cinescope.features.user.response;

import com.project.cinescope.features.user.User;

public record UserResponseDto(Integer id, String username, String firstName, String lastName, String email, String role) {
    public static UserResponseDto toResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                String.valueOf(user.getRole())
        );
    }
}
