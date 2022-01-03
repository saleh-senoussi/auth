package com.bauth.auth.service.impl;

import com.bauth.auth.entity.User;
import com.bauth.auth.entity.UserValidation;
import com.bauth.auth.error.UserValidationException;
import com.bauth.auth.error.enums.UserValidationExceptionCode;
import com.bauth.auth.helper.CodeGeneration;
import com.bauth.auth.model.UserCodeDto;
import com.bauth.auth.model.enums.UserValidationStatus;
import com.bauth.auth.repository.UserRepository;
import com.bauth.auth.repository.UserValidationRepository;
import com.bauth.auth.service.UserValidationService;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidationServiceImpl implements UserValidationService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final UserValidationRepository userValidationRepository;
  private final EmailServiceImpl emailService;

  public void validateAccount(UserCodeDto userToValidate) {
    String username = userToValidate.getUsername();
    String code = userToValidate.getCode();
    UserValidation user = userValidationRepository.findByUsername(username);

    if (passwordEncoder.matches(code, user.getCode())) {
      if (user.getUpdatedAt().isAfter(LocalDateTime.now().minusDays(1))) {
        switch (user.getStatus()) {
          case Invalid:
            user.setStatus(UserValidationStatus.Valid);
            userValidationRepository.save(user);

            User updateUser = userRepository.findByUsername(username);
            updateUser.setEnabled(true);
            userRepository.save(updateUser);
            break;
          case Valid:
            throw new UserValidationException(
                UserValidationExceptionCode.ValidatedCodeException);
          case Locked:
            throw new UserValidationException("Password reset is required.");
          default:
            throw new UserValidationException(
                UserValidationExceptionCode.OtherValidationException);
        }
      } else {
        throw new UserValidationException(
            UserValidationExceptionCode.CodeExpiredException);
      }
    } else {
      throw new UserValidationException(
          UserValidationExceptionCode.InvalidCodeException);
    }
  }

  public void createValidationCode(String username) {
    UserValidation userToValidate = new UserValidation();
    String code = CodeGeneration.generateCode();
    userToValidate.setCode(passwordEncoder.encode(code));
    userToValidate.setUsername(username);
    userValidationRepository.save(userToValidate);
    String url = String.format("http://localhost:4200/sign-in?email=%s&code=%s", username, code);
    emailService.sendValidationCode(username, "Email validation", url);
  }

  public String resendValidationCode(String username) {
    UserValidation user = userValidationRepository.findByUsername(username);
    switch (user.getStatus()) {
      case Invalid:
        String code = CodeGeneration.generateCode();
        user.setCode(passwordEncoder.encode(code));
        userValidationRepository.save(user);
        return code;
      case Valid:
        throw new UserValidationException(
            UserValidationExceptionCode.ValidatedCodeException);
      case Locked:
        throw new UserValidationException("Password reset is required.");
      default:
        throw new UserValidationException(
            UserValidationExceptionCode.OtherValidationException);
    }
  }
}
