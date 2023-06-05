package ru.otus.library.jpa.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.jpa.dao.GenresDao;
import ru.otus.library.jpa.models.Genre;

/**
 * Implementation of service responsible for operations with Genres.
 */
@Service
@AllArgsConstructor
public class GenresServiceImpl implements GenresService {
  private final GenresDao genresDao;

  @Override
  public List<Genre> findGenres() {
    return genresDao.getAll();
  }

  @Override
  public Genre getGenreById(long genreId) {
    return genresDao.getById(genreId);
  }
}
