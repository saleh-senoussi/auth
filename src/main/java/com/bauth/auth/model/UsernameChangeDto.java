package com.bauth.auth.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UsernameChangeDto {
    private final String username;
    private final String newUsername;
}