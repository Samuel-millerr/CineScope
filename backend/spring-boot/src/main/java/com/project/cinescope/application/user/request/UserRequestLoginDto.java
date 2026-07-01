package com.project.cinescope.application.user.request;

public record UserRequestLoginDto(
        String username,
        String password
) {
}
