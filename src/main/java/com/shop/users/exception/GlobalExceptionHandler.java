package com.shop.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ProblemDetail handleUserNotFound(UserNotFoundException ex) {
    return ProblemDetail.forStatusAndDetail(
        HttpStatus.NOT_FOUND,
        ex.getMessage()
    );
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ProblemDetail handleNoResourceFound(NoResourceFoundException ex) {
    return ProblemDetail.forStatusAndDetail(
        HttpStatus.NOT_FOUND,
        "Endpoint not found: " + ex.getResourcePath()
    );
  }
}