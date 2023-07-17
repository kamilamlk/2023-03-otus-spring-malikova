package ru.otus.library.flux.service;

import java.util.List;
import ru.otus.library.flux.models.Author;

/**
 * Service responsible for operations with Authors.
 */
public interface AuthorsService {
  List<Author> findAuthors();

  Author getAuthorById(String authorId);

  String addAuthor(String authorName);

  void updateAuthor(String authorId, String authorName);
}
