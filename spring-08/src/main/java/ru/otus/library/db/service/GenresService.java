package ru.otus.library.db.service;

import ru.otus.library.db.exception.NotFoundException;
import ru.otus.library.db.models.Genre;
import java.util.List;

/**
 * Service responsible for operations with Genres.
 */
public interface GenresService {
  List<Genre> findGenres();

  Genre getGenreById(long genreId) throws NotFoundException;
}
