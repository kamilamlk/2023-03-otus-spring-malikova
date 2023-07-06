package ru.otus.library.ajax.service;

import java.util.List;
import ru.otus.library.ajax.exception.NotFoundException;
import ru.otus.library.ajax.models.Genre;

/**
 * Service responsible for operations with Genres.
 */
public interface GenresService {
  List<Genre> findGenres();

  Genre getGenreById(String genreId) throws NotFoundException;
}
