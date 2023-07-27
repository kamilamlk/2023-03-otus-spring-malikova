package ru.otus.library.security.service;

import java.util.List;
import ru.otus.library.security.exception.NotFoundException;
import ru.otus.library.security.models.Genre;

/**
 * Service responsible for operations with Genres.
 */
public interface GenresService {
  List<Genre> findGenres();

  Genre getGenreById(String genreId) throws NotFoundException;
}
