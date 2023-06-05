package ru.otus.library.jpa.service;

import java.util.List;
import ru.otus.library.jpa.exception.NotFoundException;
import ru.otus.library.jpa.models.Genre;

/**
 * Service responsible for operations with Genres.
 */
public interface GenresService {
  List<Genre> findGenres();

  Genre getGenreById(long genreId) throws NotFoundException;
}
