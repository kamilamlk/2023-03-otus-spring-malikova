package ru.otus.library.flux.dao;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.flux.models.Genre;

/**
 * CRUD operations with Genres.
 */
public interface GenresDao extends MongoRepository<Genre, String> {
  @Override
  List<Genre> findAll();
}
