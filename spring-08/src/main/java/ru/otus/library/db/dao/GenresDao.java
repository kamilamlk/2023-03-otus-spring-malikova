package ru.otus.library.db.dao;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.db.models.Genre;

/**
 * CRUD operations with Genres.
 */
public interface GenresDao extends MongoRepository<Genre, String> {
  @Override
  List<Genre> findAll();
}
