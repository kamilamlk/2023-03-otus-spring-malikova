package ru.otus.library.orm.dao;

import java.util.List;
import ru.otus.library.orm.models.Comment;

/**
 * CRUD operations with Comment.
 */
public interface CommentsDao {
  Comment save(Comment comment);

  Comment getById(long id);

  List<Comment> findByBookId(long bookId);

  void update(Comment comment);

  void deleteById(long id);
}
