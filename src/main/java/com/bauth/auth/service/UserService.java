package com.bauth.auth.service;

import com.bauth.auth.model.PasswordChangeDto;
import com.bauth.auth.model.UserCreateDto;
import com.bauth.auth.model.UserResponseDto;
import com.bauth.auth.model.UsernameChangeDto;

import org.springframework.security.access.prepost.PreAuthorize;

public interface UserService {

    void createAccount(UserCreateDto userToCreate);

    // @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    UserResponseDto getAccount(String username, String token);

    // @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    void changePassword(PasswordChangeDto passwordChange, String token, String username);

    // @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    void changeUsername(UsernameChangeDto usernameChange, String token);

    // @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    void deleteAccount(String token, String username);
}
