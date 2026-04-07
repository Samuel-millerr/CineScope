package com.project.cinescope.user;

import com.project.cinescope.user.response.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAll();

    UserResponseDto getCurrentUser(Long id);
}
