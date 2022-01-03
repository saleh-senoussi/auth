package com.bauth.auth.service;

import com.bauth.auth.model.enums.UserRole;

import org.springframework.security.access.prepost.PreAuthorize;

public interface RoleService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void createRole(UserRole roleName, String username);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteRole(UserRole roleName, String username);
}
