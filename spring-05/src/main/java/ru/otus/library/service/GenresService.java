package ru.otus.library.service;

import ru.otus.library.models.Genre;

/**
 * Service responsible for operations with Genres.
 */
public interface GenresService {
  void showGenres();

  Genre getGenreById(long genreId);
}
