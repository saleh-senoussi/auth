package com.bauth.auth.model;

import com.bauth.auth.model.enums.UserRole;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RoleCreationDto {
    private final UserRole roleName;
}
