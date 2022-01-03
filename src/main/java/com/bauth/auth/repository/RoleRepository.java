package com.bauth.auth.repository;

import java.util.List;

import com.bauth.auth.entity.Role;
import com.bauth.auth.model.enums.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    List<Role> findByRoleName(UserRole roleName);
}
