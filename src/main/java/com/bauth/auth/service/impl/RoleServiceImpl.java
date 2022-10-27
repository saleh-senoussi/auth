package com.bauth.auth.service.impl;

import java.util.List;
import java.util.Optional;

import com.bauth.auth.entity.Role;
import com.bauth.auth.model.enums.UserRole;
import com.bauth.auth.repository.RoleRepository;
import com.bauth.auth.service.RoleService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public void createRole(UserRole roleName, String username) {
        Optional<List<Role>> roles = Optional.ofNullable(roleRepository.findByRoleName(roleName));
        if (roles.isEmpty()) {
            Role role = new Role();
            role.setRoleName(roleName.name());
            roleRepository.save(role);
        } else {
            throw new IllegalArgumentException("Role already exists.");
        }
    }

    public void deleteRole(UserRole roleName, String username) {
        Optional<List<Role>> roles = Optional.ofNullable(roleRepository.findByRoleName(roleName));
        if (roles.isPresent() && roles.get().size() == 1) {
            Role role = roles.get().get(0);
            roleRepository.delete(role);
        } else {
            throw new IllegalArgumentException("Role not found.");
        }
    }
}
