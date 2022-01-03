package com.bauth.auth.security.jwt.controller;

import javax.validation.Valid;

import com.bauth.auth.entity.User;
import com.bauth.auth.model.PasswordChangeDto;
import com.bauth.auth.model.PasswordResetDto;
import com.bauth.auth.model.UserCodeDto;
import com.bauth.auth.model.UserCreateDto;
import com.bauth.auth.model.UserResponseDto;
import com.bauth.auth.repository.UserRepository;
import com.bauth.auth.security.jwt.model.JwtRequest;
import com.bauth.auth.security.jwt.model.JwtResponse;
import com.bauth.auth.security.jwt.service.JwtService;
import com.bauth.auth.service.impl.PasswordResetServiceImpl;
import com.bauth.auth.service.impl.UserServiceImpl;
import com.bauth.auth.service.impl.UserValidationServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final JwtService jwtService;
    private final UserServiceImpl userService;
    private final PasswordResetServiceImpl resetService;
    private final UserValidationServiceImpl validationService;
    private final UserRepository userRepository;
    private final String AUTH_HEADER = "Authorization";

    @PostMapping("/authenticate/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        return new ResponseEntity<JwtResponse>(jwtService.createAuthenticationToken(authenticationRequest),
                HttpStatus.OK);
    }

    @PostMapping("/authenticate/create")
    public ResponseEntity<?> createAccount(@Valid @RequestBody UserCreateDto userToCreate) {
        userService.createAccount(userToCreate);
        return ResponseEntity.ok("Account created");
    }

    @GetMapping("/authenticate/{username}")
    public ResponseEntity<UserResponseDto> getAccount(@PathVariable String username,
            @RequestHeader(AUTH_HEADER) String authorization) {
        UserResponseDto user = userService.getAccount(username, authorization);
        return new ResponseEntity<UserResponseDto>(user, HttpStatus.OK);
    }

    @PostMapping("/authenticate/validate")
    public ResponseEntity<?> validateAccount(@RequestBody UserCodeDto userToValidate) {
        validationService.validateAccount(userToValidate);
        return ResponseEntity.ok("Account validated");
    }

    @GetMapping("/authenticate/reset-password/{username}")
    public ResponseEntity<?> validateAccount(@PathVariable String username) {
        resetService.passwordResetRequest(username);
        return ResponseEntity.ok("email sent");
    }

    @PostMapping("/authenticate/reset-password")
    public ResponseEntity<?> validateAccount(@RequestBody PasswordResetDto resetDto) {
        resetService.passwordResetUpdate(resetDto);
        return ResponseEntity.ok("email sent");
    }

    @PostMapping("/authenticate/change-password/{username}")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeDto passwordChange,
            @PathVariable String username, @RequestHeader(AUTH_HEADER) String token) {
        this.userService.changePassword(passwordChange, token, username);

        return ResponseEntity.ok("Account validated");
    }
}
