package com.project.cinescope.application.user.request;

import com.project.cinescope.application.user.User;

public record UserRequestRegisterDto(
        String username,
        String password,
        String firstName,
        String lastName,
        String email
) {
    public static User toEntity(UserRequestRegisterDto userRequestRegisterDto) {
        User user = new User();
        user.setUsername(userRequestRegisterDto.username());
        user.setHashedPassword(userRequestRegisterDto.password());
        user.setFirstName(userRequestRegisterDto.firstName());
        user.setLastName(userRequestRegisterDto.lastName());
        user.setEmail(userRequestRegisterDto.email());
        return user;
    }
}
