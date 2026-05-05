package com.project.cinescope.user.request;

public record UserRequestLoginDto(
        String username,
        String password
) {
}
