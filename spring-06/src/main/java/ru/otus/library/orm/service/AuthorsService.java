package ru.otus.library.orm.service;

import java.util.List;
import ru.otus.library.orm.models.Author;

/**
 * Service responsible for operations with Authors.
 */
public interface AuthorsService {
  List<Author> findAuthors();

  Author getAuthorById(long authorId);
}
