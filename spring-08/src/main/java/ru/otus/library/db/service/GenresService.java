package ru.otus.library.db.service;

import java.util.List;
import ru.otus.library.db.exception.NotFoundException;
import ru.otus.library.db.models.Genre;

/**
 * Service responsible for operations with Genres.
 */
public interface GenresService {
  List<Genre> findGenres();

  Genre getGenreById(String genreId) throws NotFoundException;
}
