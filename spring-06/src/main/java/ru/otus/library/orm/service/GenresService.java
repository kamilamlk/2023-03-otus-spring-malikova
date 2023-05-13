package ru.otus.library.orm.service;

import ru.otus.library.orm.models.Genre;

/**
 * Service responsible for operations with Genres.
 */
public interface GenresService {
  void showGenres();

  Genre getGenreById(long genreId);
}
