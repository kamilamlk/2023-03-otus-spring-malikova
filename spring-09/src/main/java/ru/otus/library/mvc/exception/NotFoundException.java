package ru.otus.library.mvc.exception;

/**
 * No element found exception.
 */
public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}
