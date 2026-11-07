package com.project.cinescope.features.user;

import com.project.cinescope.features.user.request.UserRequestRegisterDto;
import com.project.cinescope.features.user.response.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAll();

    UserResponseDto getById(Integer id);

    UserResponseDto getByUsername(String username);

    UserDetails findByUsername(String username);

    UserResponseDto findCurrentUser();

    UserResponseDto post(UserRequestRegisterDto registerDto);
}
