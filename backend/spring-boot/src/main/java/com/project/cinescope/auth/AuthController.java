package com.project.cinescope.auth;

import com.project.cinescope.auth.service.TokenService;
import com.project.cinescope.user.User;
import com.project.cinescope.user.UserService;
import com.project.cinescope.user.request.UserRequestLoginDto;
import com.project.cinescope.user.request.UserRequestRegisterDto;
import com.project.cinescope.auth.response.TokenResponseDto;
import com.project.cinescope.user.response.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserRequestLoginDto requestDto) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(requestDto.username(), requestDto.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRequestRegisterDto requestDto) {
        if (userService.findByUsername(requestDto.username()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String hashedPassword = new BCryptPasswordEncoder().encode(requestDto.password());

        UserRequestRegisterDto registerDto = new UserRequestRegisterDto(
                requestDto.username(),
                hashedPassword,
                requestDto.firstName(),
                requestDto.lastName(),
                requestDto.email()
        );

        UserResponseDto responseDto = userService.post(registerDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
