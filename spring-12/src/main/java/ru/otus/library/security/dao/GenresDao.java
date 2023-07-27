package ru.otus.library.security.dao;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.security.models.Genre;

/**
 * CRUD operations with Genres.
 */
public interface GenresDao extends MongoRepository<Genre, String> {
  @Override
  List<Genre> findAll();
}
