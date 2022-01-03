package com.bauth.auth.service.impl;

import java.util.List;
import java.util.Optional;

import com.bauth.auth.entity.Permission;
import com.bauth.auth.model.enums.UserPermission;
import com.bauth.auth.repository.PermissionRepository;
import com.bauth.auth.service.PermissionService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public void createPermission(UserPermission permissionName, String username) {
        Optional<List<Permission>> permissions = Optional
                .ofNullable(permissionRepository.findByPermissionName(permissionName));
        if (permissions.isEmpty()) {
            Permission permission = new Permission();
            permission.setPermissionName(permissionName);
            permissionRepository.save(permission);
        } else {
            throw new IllegalArgumentException("Permission already exists.");
        }
    }

    @Override
    public void deletePermission(UserPermission permissionName, String username) {
        Optional<List<Permission>> permissions = Optional
                .ofNullable(permissionRepository.findByPermissionName(permissionName));
        if (permissions.isPresent() && permissions.get().size() == 1) {
            Permission permission = permissions.get().get(0);
            permissionRepository.delete(permission);
        } else {
            throw new IllegalArgumentException("Role not found.");
        }
    }

}
