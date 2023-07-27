package ru.otus.library.security.exception;

/**
 * No element found exception.
 */
public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}
