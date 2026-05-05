package com.project.cinescope.user;

import com.project.cinescope.user.request.UserRequestRegisterDto;
import com.project.cinescope.user.response.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAll();

    UserDetails findByUsername(String username);

    UserResponseDto post(UserRequestRegisterDto registerDto);
}
