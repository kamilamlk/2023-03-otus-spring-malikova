package ru.otus.library.db.service;

import ru.otus.library.db.exception.NotFoundException;
import ru.otus.library.db.models.Author;
import java.util.List;

/**
 * Service responsible for operations with Authors.
 */
public interface AuthorsService {
  List<Author> findAuthors();

  Author getAuthorById(long authorId) throws NotFoundException;
}
