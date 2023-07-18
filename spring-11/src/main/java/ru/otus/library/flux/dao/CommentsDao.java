package ru.otus.library.flux.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.flux.models.Comment;

/**
 * CRUD operations with Comment.
 */
public interface CommentsDao extends MongoRepository<Comment, String> {
  void deleteByBook_Id(String bookId);

  void findAllByBook_Id(String bookId);
}
