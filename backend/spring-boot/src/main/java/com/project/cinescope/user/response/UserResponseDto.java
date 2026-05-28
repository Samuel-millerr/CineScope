package com.project.cinescope.user.response;

import com.project.cinescope.user.User;

public record UserResponseDto(Long id, String username, String firstName, String lastName, String email, String role) {
    public static UserResponseDto toUserDto(User user) {
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
