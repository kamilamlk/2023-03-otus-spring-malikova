package ru.otus.library.orm.service;

import java.util.List;
import ru.otus.library.orm.models.Genre;

/**
 * Service responsible for operations with Genres.
 */
public interface GenresService {
  List<Genre> findGenres();

  Genre getGenreById(long genreId);
}
