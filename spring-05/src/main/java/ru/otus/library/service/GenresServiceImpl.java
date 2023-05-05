package ru.otus.library.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dao.GenresDao;
import ru.otus.library.models.Genre;

/**
 * Implementation of service responsible for operations with Genres.
 */
@Service
@AllArgsConstructor
public class GenresServiceImpl implements GenresService {
  private final GenresDao genresDao;
  private final IoService ioService;

  @Override
  public void showGenres() {
    List<Genre> genres = genresDao.getAll();
    genres.forEach(ioService::writeGenre);
  }

  @Override
  public Genre getGenreById(long genreId) {
    return genresDao.getById(genreId);
  }
}
