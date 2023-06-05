package ru.otus.library.jpa.dao;

import java.util.List;
import ru.otus.library.jpa.models.Genre;

/**
 * CRUD operations with Genres.
 */
public interface GenresDao {
  Genre save(Genre genre);

  List<Genre> getAll();

  Genre getById(long id);

  void update(Genre genre);

  void delete(Genre genre);
}
