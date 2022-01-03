package com.bauth.auth.service;

import com.bauth.auth.model.UserCodeDto;

public interface UserValidationService {

    void validateAccount(UserCodeDto userToValidate);

    void createValidationCode(String username);

    String resendValidationCode(String username);
}
