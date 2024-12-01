package com.duartedot.backend.exception;

public class AlreadyRegisteredCpf extends RuntimeException {
  public AlreadyRegisteredCpf(String message) {
    super(message);
  }
}
