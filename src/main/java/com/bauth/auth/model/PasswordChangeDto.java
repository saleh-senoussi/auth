package com.bauth.auth.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PasswordChangeDto {
    private final String password;
    private final String newPassword;
    private final String confirmNewPassword;
}
