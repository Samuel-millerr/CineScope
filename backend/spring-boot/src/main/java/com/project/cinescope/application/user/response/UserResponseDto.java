package com.project.cinescope.application.user.response;

import com.project.cinescope.application.user.User;

public record UserResponseDto(Long id, String username, String firstName, String lastName, String email, String role) {
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
