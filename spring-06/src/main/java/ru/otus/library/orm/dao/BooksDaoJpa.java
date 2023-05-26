package ru.otus.library.orm.dao;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    EntityGraph<?> entityGraph = entityManager.getEntityGraph("book-comments-entity-graph");
    Map<String, Object> properties = new HashMap<>();
    properties.put("javax.persistence.fetchgraph", entityGraph);
    return entityManager.find(Book.class, id, properties);
  }

  @Override
  public void update(Book book) {
    entityManager.merge(book);
  }

  @Override
  public void delete(Book book) {
    entityManager.remove(book);
  }

  @Override
  public List<Book> getAll() {
    TypedQuery<Book> query = entityManager.createQuery(
            "select distinct b from Book b join fetch b.author join fetch b.genre",
            Book.class);
    return query.getResultList();
  }
}
