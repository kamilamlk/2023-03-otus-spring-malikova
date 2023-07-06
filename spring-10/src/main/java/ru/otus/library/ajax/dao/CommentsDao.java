package ru.otus.library.ajax.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.ajax.models.Comment;

/**
 * CRUD operations with Comment.
 */
public interface CommentsDao extends MongoRepository<Comment, String> {
  void deleteByBook_Id(String bookId);
}
