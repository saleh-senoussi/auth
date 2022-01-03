package com.bauth.auth.error;

import com.bauth.auth.error.enums.UserAccountExceptionCode;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.ToString;

@ToString
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserBadRequestException extends RuntimeException {
    private String message = UserAccountExceptionCode.BadRequestException.label;
    private String code = UserAccountExceptionCode.BadRequestException.name();
    private final static Throwable cause = null;

	public UserBadRequestException() {
		super(UserAccountExceptionCode.BadRequestException.label);
	}

    public UserBadRequestException(UserAccountExceptionCode code) {
        super(code.label, null, true, false);
        this.code = code.name();
        this.message = code.label;
	}

	public UserBadRequestException(String message) {
		super(message, null, true, false);
	}

	public UserBadRequestException(Throwable cause) {
		super(cause);
	}

	public UserBadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserBadRequestException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}

