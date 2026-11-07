package com.project.cinescope.features.user.impl;

import com.project.cinescope.features.auth.service.AuthenticatedUserService;
import com.project.cinescope.features.user.User;
import com.project.cinescope.features.user.UserRepository;
import com.project.cinescope.features.user.UserService;
import com.project.cinescope.features.user.request.UserRequestRegisterDto;
import com.project.cinescope.features.user.response.UserResponseDto;
import com.project.cinescope.core.exception.DefaultException;
import com.project.cinescope.core.utils.message.ErrorEnum;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticatedUserService authenticatedUserService;

    public UserServiceImpl(UserRepository userRepository, AuthenticatedUserService authenticatedUserService) {
        this.userRepository = userRepository;
        this.authenticatedUserService = authenticatedUserService;
    }

    @Override
    public List<UserResponseDto> getAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(UserResponseDto::toResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto getById(Integer id) {
        return userRepository.findById(id)
                .map(UserResponseDto::toResponseDto)
                .orElseThrow(() -> new DefaultException(
                                HttpStatus.NOT_FOUND,
                                ErrorEnum.NOT_FOUND.formatDetailedTemplate("User", "id", id)
                        )
                );
    }

    @Override
    public UserResponseDto getByUsername(String username) {
        User user = userRepository.getByUsername(username);

        if (user == null) {
            throw new DefaultException(
                    HttpStatus.NOT_FOUND,
                    ErrorEnum.NOT_FOUND.formatDetailedTemplate("User", "username", username)
            );
        }

        return UserResponseDto.toResponseDto(user);
    }

    @Override
    public UserDetails findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserResponseDto findCurrentUser() {
        User user = authenticatedUserService.getCurrentUser();
        return UserResponseDto.toResponseDto(user);
    }

    @Override
    public UserResponseDto post(UserRequestRegisterDto registerDto) {
        String username = registerDto.username();
        if (userRepository.existsByUsername(username)) {
            throw new DefaultException(HttpStatus.CONFLICT,
                    ErrorEnum.DUPLICATE.formatDetailedTemplate("User", "username", username)
            );
        }

        String userEmail = registerDto.email();
        if (userRepository.existsByEmail(userEmail)) {
            throw new DefaultException(HttpStatus.CONFLICT,
                    ErrorEnum.DUPLICATE.formatDetailedTemplate("User", "email", userEmail)
            );
        }

        User user = UserRequestRegisterDto.toEntity(registerDto);
        User createdUser = userRepository.save(user);
        return UserResponseDto.toResponseDto(createdUser);
    }
}
