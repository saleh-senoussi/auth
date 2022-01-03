package com.bauth.auth.error.enums;

public enum UserValidationExceptionCode implements BaseErrorEnum {
    CodeExpiredException("Validation code expired, it expires after 24 hours."),
    InvalidCodeException("Invalid code provided."),
    ValidatedCodeException("Provided code is already validated"),
    OtherValidationException("An error occured while validating account.");

    public final String label;

    private UserValidationExceptionCode(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}
