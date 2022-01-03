package com.bauth.auth.error;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

  @ExceptionHandler(value = UserBadRequestException.class)
  public ResponseEntity<UserBadRequestException> userBadRequestException(
    UserBadRequestException userBadRequestException
  ) {
    return new ResponseEntity<UserBadRequestException>(
      userBadRequestException,
      HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(value = UserValidationException.class)
  public ResponseEntity<UserValidationException> userValidationException(
    UserValidationException userValidationException
  ) {
    return new ResponseEntity<UserValidationException>(
      userValidationException,
      HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(value = ConstraintViolationException.class)
  public ResponseEntity<ConstraintViolationException> runtimeException(ConstraintViolationException constraintViolationException) {
    return new ResponseEntity<ConstraintViolationException>(constraintViolationException, HttpStatus.BAD_REQUEST);
  }
}
