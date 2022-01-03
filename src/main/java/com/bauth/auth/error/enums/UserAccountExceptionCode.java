package com.bauth.auth.error.enums;

public enum UserAccountExceptionCode implements BaseErrorEnum {
    PasswordResetRequiredException("Password reset is required for this account."),
    ValidationRequiredException("You need to validate your account before signing in."),
    UsernameExistsException("The username provided already exists."),
    BadRequestException("Conflict"),
    InvalidConfirmNewPasswordException("Cnnfirm new password does not match new password"),
    InternalServerException("Internal error."),
    InvalidCredentialsException("Invalid username or password.");

    public final String label;

    private UserAccountExceptionCode(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}
