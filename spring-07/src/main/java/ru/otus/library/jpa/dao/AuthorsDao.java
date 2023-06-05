package ru.otus.library.jpa.dao;

import java.util.List;
import ru.otus.library.jpa.models.Author;

/**
 * CRUD operations with Author.
 */
public interface AuthorsDao {
  Author save(Author author);

  List<Author> getAll();

  Author getById(long id);

  void update(Author author);

  void delete(Author author);
}
