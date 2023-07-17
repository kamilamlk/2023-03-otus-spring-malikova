package ru.otus.library.flux.service;

import java.util.List;
import ru.otus.library.flux.exception.NotFoundException;
import ru.otus.library.flux.models.Genre;

/**
 * Service responsible for operations with Genres.
 */
public interface GenresService {
  List<Genre> findGenres();

  Genre getGenreById(String genreId) throws NotFoundException;
}
