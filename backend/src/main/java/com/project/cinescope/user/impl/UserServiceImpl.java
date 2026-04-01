package com.project.cinescope.user.impl;

import com.project.cinescope.exception.ResourceNotFoundException;
import com.project.cinescope.user.User;
import com.project.cinescope.user.UserRepository;
import com.project.cinescope.user.UserService;
import com.project.cinescope.user.response.UserResponseDto;
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

    public UserResponseDto getCurrentUser(Long id) {
        return userRepository.findById(id)
                .map(UserResponseDto::toUserDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }
}
