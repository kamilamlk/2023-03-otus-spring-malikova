package ru.otus.library.orm.dao;

import java.util.List;
import ru.otus.library.orm.models.Genre;

/**
 * CRUD operations with Genres.
 */
public interface GenresDao {
  Genre save(Genre genre);

  List<Genre> getAll();

  Genre getById(long id);
}
