package com.bauth.auth.model;

import com.bauth.auth.model.enums.UserPermission;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PermissionCreationDto {
    private final UserPermission userPermission;
}
