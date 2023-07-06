package ru.otus.library.ajax.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.ajax.dao.GenresDao;
import ru.otus.library.ajax.exception.NotFoundException;
import ru.otus.library.ajax.models.Genre;

/**
 * Implementation of service responsible for operations with Genres.
 */
@Service
@AllArgsConstructor
public class GenresServiceImpl implements GenresService {
  private final GenresDao genresDao;

  @Override
  public List<Genre> findGenres() {
    return genresDao.findAll();
  }

  @Override
  public Genre getGenreById(String genreId) {
    return genresDao.findById(genreId)
                   .orElseThrow(() -> new NotFoundException("Genre is not found"));
  }
}
