package ru.otus.library.ajax.dao;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.ajax.models.Genre;

/**
 * CRUD operations with Genres.
 */
public interface GenresDao extends MongoRepository<Genre, String> {
  @Override
  List<Genre> findAll();
}
