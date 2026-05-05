package com.project.cinescope.user.impl;

import com.project.cinescope.exception.exceptions.ResourceNotFoundException;
import com.project.cinescope.user.User;
import com.project.cinescope.user.UserRepository;
import com.project.cinescope.user.UserService;
import com.project.cinescope.user.request.UserRequestRegisterDto;
import com.project.cinescope.user.response.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> getAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(UserResponseDto::toUserDto)
                .toList();
    }

    public UserDetails findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserResponseDto post(UserRequestRegisterDto registerDto) {
        User user = UserRequestRegisterDto.toUser(registerDto);
        User createdUser = userRepository.save(user);
        return  UserResponseDto.toUserDto(createdUser);
    }
}
