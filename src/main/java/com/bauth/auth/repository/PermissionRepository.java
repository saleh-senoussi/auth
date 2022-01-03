package com.bauth.auth.repository;

import java.util.List;

import com.bauth.auth.entity.Permission;
import com.bauth.auth.model.enums.UserPermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String>  {
    List<Permission> findByPermissionName(UserPermission permission);
}
