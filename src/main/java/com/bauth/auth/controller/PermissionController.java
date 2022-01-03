package com.bauth.auth.controller;

import com.bauth.auth.model.PermissionCreationDto;
import com.bauth.auth.service.PermissionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping("/permission/{username}")
    public ResponseEntity<?> createPermission(@RequestBody PermissionCreationDto permissionDto, @PathVariable String username) {
        permissionService.createPermission(permissionDto.getUserPermission(), username);
        return ResponseEntity.ok("Permission created.");
    }

    @DeleteMapping("/permission/{username}")
    public ResponseEntity<?> deletePermission(@RequestBody PermissionCreationDto permissionDto, @PathVariable String username) {
        permissionService.deletePermission(permissionDto.getUserPermission(), username);
        return ResponseEntity.ok("Role created.");
    }
}
