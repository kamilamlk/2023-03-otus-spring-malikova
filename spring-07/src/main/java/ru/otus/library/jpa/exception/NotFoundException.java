package ru.otus.library.jpa.exception;

/**
 * No element found exception.
 */
public class NotFoundException extends Exception {
  public NotFoundException(String message) {
    super(message);
  }
}
