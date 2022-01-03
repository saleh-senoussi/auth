package com.bauth.auth.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.bauth.auth.validation.validator.PasswordValidator;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "The password must have at least one numeric character, at least one lowercase character, at least one uppercase character, t least one special symbol among @#$%. Password length should be between 8 and 20.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
