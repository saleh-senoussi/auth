package com.bauth.auth.repository;

import com.bauth.auth.entity.PasswordReset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordReset, String> {
    PasswordReset findByUsername(String username);
}
