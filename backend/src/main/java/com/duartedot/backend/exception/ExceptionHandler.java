package com.duartedot.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {
  @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Map<String, String>>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, Map<String, String>> errorResponse = new HashMap<>();
    Map<String, String> errors = new HashMap<>();

    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.put(error.getField(), error.getDefaultMessage());
    }

    errorResponse.put("erro", errors);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(AlreadyRegisteredCpf.class)
  public ResponseEntity<Map<String, String>> handleAlreadyRegisteredCpfException(
      AlreadyRegisteredCpf e) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("erro", e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFound.class)
  public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFound e) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("erro", e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }
}
