package ru.otus.library.jpa.service;

import java.util.List;
import ru.otus.library.jpa.models.Author;

/**
 * Service responsible for operations with Authors.
 */
public interface AuthorsService {
  List<Author> findAuthors();

  Author getAuthorById(long authorId);
}
