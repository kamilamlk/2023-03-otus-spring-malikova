package ru.otus.library.db.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.library.db.models.Comment;

/**
 * CRUD operations with Comment.
 */
public interface CommentsDao extends MongoRepository<Comment, String> {
  void deleteByBook_Id(String bookId);
}
