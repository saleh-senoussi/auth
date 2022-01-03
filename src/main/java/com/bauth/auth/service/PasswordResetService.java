package com.bauth.auth.service;

import com.bauth.auth.model.PasswordResetDto;

public interface PasswordResetService {
    void passwordResetRequest(String username);
    void passwordResetUpdate(PasswordResetDto passwordReset);
}
