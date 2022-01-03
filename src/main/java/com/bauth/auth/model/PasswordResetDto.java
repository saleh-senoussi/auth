package com.bauth.auth.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PasswordResetDto {
    private final String username;
    private final String code;
    private final String password;
    private final String confirmPassword;
}
