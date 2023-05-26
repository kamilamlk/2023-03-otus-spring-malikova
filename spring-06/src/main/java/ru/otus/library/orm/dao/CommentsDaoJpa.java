package ru.otus.library.orm.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.otus.library.orm.models.Comment;

/**
 * Implementation of CRUD operations with Comment.
 */
@Repository
public class CommentsDaoJpa implements CommentsDao {
  @PersistenceContext
  private final EntityManager em;

  public CommentsDaoJpa(EntityManager em) {
    this.em = em;
  }

  @Override
  public Comment save(Comment comment) {
    if (comment.getId() <= 0) {
      em.persist(comment);
      return comment;
    } else {
      return em.merge(comment);
    }
  }


  @Override
  public Comment getById(long id) {
    return em.find(Comment.class, id);
  }

  @Override
  public void update(Comment comment) {
    em.merge(comment);
  }

  @Override
  public void delete(Comment comment) {
    em.remove(comment);
  }
}
