package ru.otus.library.security.dao;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.security.models.Author;

/**
 * CRUD operations with Author.
 */
public interface AuthorsDao extends MongoRepository<Author, String> {
  @Override
  List<Author> findAll();
}
