package ru.otus.library.orm.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository;
import ru.otus.library.orm.models.Book;

/**
 * Implementation of CRUD operations with Books.
 */
@Repository
public class BooksDaoJpa implements BooksDao {
  @PersistenceContext
  private final EntityManager entityManager;

  public BooksDaoJpa(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Book save(Book book) {
    entityManager.persist(book);
    return book;
  }

  @Override
  public Book getById(long id) {
    return entityManager.find(Book.class, id);
  }

  @Override
  public void update(Book book) {
    entityManager.merge(book);
  }

  @Override
  public void deleteById(long id) {
    Query query = entityManager.createQuery("delete from Book b where b.id = :id ");
    query.setParameter("id", id);
    query.executeUpdate();
  }

  @Override
  public List<Book> getAll() {
    TypedQuery<Book> query = entityManager.createQuery(
            "select distinct b from Book b join fetch b.author join fetch b.genre",
            Book.class);
    return query.getResultList();
  }
}
