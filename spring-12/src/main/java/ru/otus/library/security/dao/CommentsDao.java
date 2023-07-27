package ru.otus.library.security.dao;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.security.models.Comment;

/**
 * CRUD operations with Comment.
 */
public interface CommentsDao extends MongoRepository<Comment, String> {
  void deleteByBook_Id(String bookId);

  List<Comment> findAllByBook_Id(String bookId);
}
