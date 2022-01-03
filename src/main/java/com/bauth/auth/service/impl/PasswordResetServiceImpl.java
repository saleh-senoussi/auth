package com.bauth.auth.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import com.bauth.auth.entity.PasswordReset;
import com.bauth.auth.entity.User;
import com.bauth.auth.error.UserBadRequestException;
import com.bauth.auth.error.UserValidationException;
import com.bauth.auth.error.enums.UserAccountExceptionCode;
import com.bauth.auth.error.enums.UserValidationExceptionCode;
import com.bauth.auth.helper.CodeGeneration;
import com.bauth.auth.model.PasswordResetDto;
import com.bauth.auth.model.enums.PasswordResetStatus;
import com.bauth.auth.repository.PasswordResetRepository;
import com.bauth.auth.repository.UserRepository;
import com.bauth.auth.service.PasswordResetService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final EmailServiceImpl emailService;
    private final PasswordResetRepository resetRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void passwordResetRequest(String username) {
        Optional<User> userCheck = Optional.ofNullable(userRepository.findByUsername(username));

        if (userCheck.isPresent()) {
            String code = CodeGeneration.generateCode();

            PasswordReset passwordReset = new PasswordReset();
            passwordReset.setUsername(username);
            passwordReset.setCode(passwordEncoder.encode(code));

            resetRepository.save(passwordReset);
            userCheck.get().setPasswordExpired(true);
            userRepository.save(userCheck.get());

            emailService.sendPasswordResetCode(username, "Password reset", code);
        } else {
            throw new UserBadRequestException(UserAccountExceptionCode.UsernameExistsException);
        }
    }

    public void passwordResetUpdate(PasswordResetDto passwordReset) {
        String username = passwordReset.getUsername();
        String code = passwordReset.getCode();
        Optional<PasswordReset> user = Optional.ofNullable(resetRepository.findByUsername(username));
        Optional<User> userToUpdate = Optional.ofNullable(userRepository.findByUsername(username));

        if (user.isPresent() && userToUpdate.isPresent()) {
            if (passwordEncoder.matches(code, user.get().getCode())) {
                if (user.get().getUpdatedAt().isAfter(LocalDateTime.now().minusDays(1))) {
                    user.get().setStatus(PasswordResetStatus.NoRequest);
                    user.get().setStatus(PasswordResetStatus.NoRequest);
                    resetRepository.save(user.get());
    
                    userToUpdate.get().setPassword(passwordEncoder.encode(passwordReset.getConfirmPassword()));
                    userToUpdate.get().setPasswordExpired(false);
                    userRepository.save(userToUpdate.get());
                } else {
                    throw new UserValidationException(
                            UserValidationExceptionCode.CodeExpiredException);
                }
            } else {
                throw new UserValidationException(
                        UserValidationExceptionCode.InvalidCodeException);
            }
        }
    }
}
