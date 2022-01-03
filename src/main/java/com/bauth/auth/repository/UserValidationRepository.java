package com.bauth.auth.repository;

import com.bauth.auth.entity.UserValidation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserValidationRepository extends JpaRepository<UserValidation, String> {
    UserValidation findByUsername(String username);
}
