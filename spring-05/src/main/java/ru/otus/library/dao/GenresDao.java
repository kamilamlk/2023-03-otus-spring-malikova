package ru.otus.library.dao;

import java.util.List;
import ru.otus.library.models.Genre;

/**
 * CRUD operations with Genres.
 */
public interface GenresDao {
  List<Genre> getAll();

  Genre getById(long id);
}
