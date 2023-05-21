package ru.otus.library.orm.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
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
  public List<Comment> findByBookId(long bookId) {
    TypedQuery<Comment> query = em.createQuery(
            "select distinct c from Comment c join fetch c.book where c.book.id = :book_id",
            Comment.class
    );
    query.setParameter("book_id", bookId);
    return query.getResultList();
  }

  @Override
  public void update(Comment comment) {
    em.merge(comment);
  }

  @Override
  public void deleteById(long id) {
    Query query = em.createQuery("delete from Comment c where c.id = :id ");
    query.setParameter("id", id);
    query.executeUpdate();
  }
}
