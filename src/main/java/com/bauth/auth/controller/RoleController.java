package com.bauth.auth.controller;

import com.bauth.auth.model.RoleCreationDto;
import com.bauth.auth.service.RoleService;

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
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/role")
    public ResponseEntity<?> createRole(@RequestBody RoleCreationDto roleDto, @PathVariable String username) {
        roleService.createRole(roleDto.getRoleName(), username);
        return ResponseEntity.ok("Role created.");
    }

    @DeleteMapping("/role")
    public ResponseEntity<?> deleteRole(@RequestBody RoleCreationDto roleDto, @PathVariable String username) {
        roleService.deleteRole(roleDto.getRoleName(), username);
        return ResponseEntity.ok("Role created.");
    }
}
