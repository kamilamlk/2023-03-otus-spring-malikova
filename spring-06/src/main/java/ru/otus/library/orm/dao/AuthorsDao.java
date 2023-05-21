package ru.otus.library.orm.dao;

import java.util.List;
import ru.otus.library.orm.models.Author;

/**
 * CRUD operations with Author.
 */
public interface AuthorsDao {
  Author save(Author author);

  List<Author> getAll();

  Author getById(long id);
}
