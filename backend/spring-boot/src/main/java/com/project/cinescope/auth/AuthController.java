package com.project.cinescope.auth;

import com.project.cinescope.auth.response.TokenResponseDto;
import com.project.cinescope.auth.service.TokenService;
import com.project.cinescope.exception.exceptions.InvalidCredentialsException;
import com.project.cinescope.health.HealthCheckService;
import com.project.cinescope.health.response.HealthCheckResponseDto;
import com.project.cinescope.user.User;
import com.project.cinescope.user.UserService;
import com.project.cinescope.user.request.UserRequestLoginDto;
import com.project.cinescope.user.request.UserRequestRegisterDto;
import com.project.cinescope.user.response.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;
    private final HealthCheckService healthCheckService;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, TokenService tokenService, HealthCheckService healthCheckService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
        this.healthCheckService = healthCheckService;
    }

    @GetMapping("/health")
    public ResponseEntity<HealthCheckResponseDto> health() {
        return ResponseEntity.ok(healthCheckService.healthCheck(this.getClass()));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid UserRequestLoginDto requestDto) {
        try {
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(requestDto.username(), requestDto.password());
            Authentication auth = authenticationManager.authenticate(usernamePassword);

            String token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new TokenResponseDto(token));
        } catch (InternalAuthenticationServiceException | BadCredentialsException e) {
            throw new InvalidCredentialsException("Username or password incorrect");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserRequestRegisterDto requestDto) {
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
