package ru.otus.library.mvc.service;

import java.util.List;
import ru.otus.library.mvc.exception.NotFoundException;
import ru.otus.library.mvc.models.Genre;

/**
 * Service responsible for operations with Genres.
 */
public interface GenresService {
  List<Genre> findGenres();

  Genre getGenreById(String genreId) throws NotFoundException;
}
