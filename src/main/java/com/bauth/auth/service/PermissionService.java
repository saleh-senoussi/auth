package com.bauth.auth.service;

import com.bauth.auth.model.enums.UserPermission;

import org.springframework.security.access.prepost.PreAuthorize;

public interface PermissionService {
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void createPermission(UserPermission permission, String username);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deletePermission(UserPermission permission, String username);
}
