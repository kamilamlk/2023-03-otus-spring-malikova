package ru.otus.library.dao;

import java.util.List;
import ru.otus.library.models.Author;

/**
 * CRUD operations with Author.
 */
public interface AuthorsDao {
  List<Author> getAll();

  Author getById(long id);
}
