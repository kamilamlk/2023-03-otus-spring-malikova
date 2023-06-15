package ru.otus.library.db.service;

import java.util.List;
import ru.otus.library.db.exception.NotFoundException;
import ru.otus.library.db.models.Author;

/**
 * Service responsible for operations with Authors.
 */
public interface AuthorsService {
  List<Author> findAuthors();

  Author getAuthorById(String authorId) throws NotFoundException;
}
