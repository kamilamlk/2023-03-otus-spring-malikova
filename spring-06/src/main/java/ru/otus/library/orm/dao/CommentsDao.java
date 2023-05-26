package ru.otus.library.orm.dao;

import ru.otus.library.orm.models.Comment;

/**
 * CRUD operations with Comment.
 */
public interface CommentsDao {
  Comment save(Comment comment);

  Comment getById(long id);

  void update(Comment comment);

  void delete(Comment comment);
}
