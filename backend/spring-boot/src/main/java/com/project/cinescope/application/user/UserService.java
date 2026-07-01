package com.project.cinescope.application.user;

import com.project.cinescope.application.user.request.UserRequestRegisterDto;
import com.project.cinescope.application.user.response.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAll();

    UserResponseDto getById(Long id);

    UserResponseDto getByUsername(String username);

    UserDetails findByUsername(String username);

    UserResponseDto post(UserRequestRegisterDto registerDto);
}
