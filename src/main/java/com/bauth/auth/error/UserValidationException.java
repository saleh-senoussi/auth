package com.bauth.auth.error;

import com.bauth.auth.error.enums.UserValidationExceptionCode;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.ToString;

@ToString
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserValidationException extends RuntimeException {
    private String message = UserValidationExceptionCode.OtherValidationException.label;
    private String code = UserValidationExceptionCode.OtherValidationException.name();

	public UserValidationException() {
		super(UserValidationExceptionCode.OtherValidationException.label);
	}

    public UserValidationException(UserValidationExceptionCode code) {
        super(code.label);
        this.code = code.name();
        this.message = code.label;
	}

	public UserValidationException(String message) {
		super(message);
	}

	public UserValidationException(Throwable cause) {
		super(cause);
	}

	public UserValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserValidationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
