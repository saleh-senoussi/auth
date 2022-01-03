package com.bauth.auth.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bauth.auth.validation.UsernameConstraint;

public class UsernameValidator implements 
ConstraintValidator<UsernameConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("[0-9]+")
        && (value.length() > 8) && (value.length() < 14);
    }
    
}
