package ru.otus.library.service;

import ru.otus.library.models.Author;

/**
 * Service responsible for operations with Authors.
 */
public interface AuthorsService {
  void showAuthors();

  Author getAuthorById(long authorId);
}
