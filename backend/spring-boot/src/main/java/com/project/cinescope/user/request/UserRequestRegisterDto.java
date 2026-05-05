package com.project.cinescope.user.request;

import com.project.cinescope.user.User;

public record UserRequestRegisterDto(
        String username,
        String password,
        String firstName,
        String lastName,
        String email
) {
    public static User toUser(UserRequestRegisterDto userRequestRegisterDto) {
        User user = new User();
        user.setUsername(userRequestRegisterDto.username());
        user.setHashedPassword(userRequestRegisterDto.password());
        user.setFirstName(userRequestRegisterDto.firstName());
        user.setLastName(userRequestRegisterDto.lastName());
        user.setEmail(userRequestRegisterDto.email());
        return user;
    }
}
