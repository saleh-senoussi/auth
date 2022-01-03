package com.bauth.auth.service.impl;

import com.bauth.auth.entity.User;
import com.bauth.auth.entity.UserValidation;
import com.bauth.auth.error.UserBadRequestException;
import com.bauth.auth.error.enums.UserAccountExceptionCode;
import com.bauth.auth.model.PasswordChangeDto;
import com.bauth.auth.model.UserCreateDto;
import com.bauth.auth.model.UserResponseDto;
import com.bauth.auth.model.UsernameChangeDto;
import com.bauth.auth.model.enums.UserValidationStatus;
import com.bauth.auth.repository.UserRepository;
import com.bauth.auth.repository.UserValidationRepository;
import com.bauth.auth.security.jwt.config.JwtTokenUtil;
import com.bauth.auth.service.UserService;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final UserValidationServiceImpl validationService;
    private final UserValidationRepository validationRepository;

    public void createAccount(UserCreateDto userToCreate) {
        Optional<User> userCheck = Optional.ofNullable(
                userRepository.findByUsername(userToCreate.getUsername()));
        if (userCheck.isEmpty()) {
            User user = new User();
            user.setUsername(userToCreate.getUsername());
            user.setPassword(passwordEncoder.encode(userToCreate.getPassword()));
            user.setFirstName(userToCreate.getFirstName());
            user.setLastName(userToCreate.getLastName());
            user.setPhoneNumber(userToCreate.getPhoneNumber());
            userRepository.save(user);
            validationService.createValidationCode(userToCreate.getUsername());
        } else {
            throw new UserBadRequestException(
                    UserAccountExceptionCode.UsernameExistsException);
        }
    }

    public UserResponseDto getAccount(String username, String token) {
        if (jwtTokenUtil.isCurrentUser(username, token)) {
            Optional<User> user = Optional.ofNullable(
                    userRepository.findByUsername(username));
            if (user.isPresent()) {
                UserResponseDto response = new UserResponseDto();
                response.setUsername(user.get().getUsername());
                response.setFirstName(user.get().getFirstName());
                response.setLastName(user.get().getLastName());
                response.setPhoneNumber(user.get().getPhoneNumber());
                response.setImageUrl(user.get().getImageUrl());
                return response;
            }

            throw new UserBadRequestException(
                    UserAccountExceptionCode.BadRequestException);
        }
        throw new UserBadRequestException(
                UserAccountExceptionCode.InvalidCredentialsException);
    }

    public void changePassword(
            PasswordChangeDto passwordChange,
            String token,
            String username) {
        if (jwtTokenUtil.isCurrentUser(username, token)) {
            if (passwordChange.getNewPassword().equals(passwordChange.getConfirmNewPassword())) {
                User user = userRepository.findByUsername(username);
                user.setPassword(passwordEncoder.encode(passwordChange.getConfirmNewPassword()));
                userRepository.save(user);
                return;
            }
            throw new UserBadRequestException(
                    UserAccountExceptionCode.InvalidConfirmNewPasswordException);
        }
        throw new UserBadRequestException(
                UserAccountExceptionCode.BadRequestException);
    }

    public void changeUsername(UsernameChangeDto usernameChange, String token) {
        if (jwtTokenUtil.isCurrentUser(usernameChange.getUsername(), token)) {
            User user = userRepository.findByUsername(usernameChange.getUsername());
            user.setUsername(usernameChange.getNewUsername());
            user.setEnabled(false);
            userRepository.save(user);

            UserValidation validation = validationRepository.findByUsername(usernameChange.getUsername());
            validation.setStatus(UserValidationStatus.Invalid);
            validationRepository.save(validation);
        } else {
            throw new UserBadRequestException(
                    UserAccountExceptionCode.UsernameExistsException);
        }
    }

    public void deleteAccount(String token, String username) {
        if (jwtTokenUtil.isCurrentUser(username, token)) {
            Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
            if (user.isPresent()) {
                userRepository.deleteByUsername(username);
            } else {
                throw new UserBadRequestException(
                        UserAccountExceptionCode.UsernameExistsException);
            }
        } else {
            throw new UserBadRequestException(
                    UserAccountExceptionCode.UsernameExistsException);
        }
    }
}
