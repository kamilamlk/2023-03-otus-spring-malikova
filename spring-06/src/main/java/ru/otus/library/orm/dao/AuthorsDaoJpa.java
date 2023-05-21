package ru.otus.library.orm.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.library.orm.models.Author;

/**
 * Implementation of CRUD operations with Author.
 */
@Repository
public class AuthorsDaoJpa implements AuthorsDao {
  @PersistenceContext
  private final EntityManager em;

  public AuthorsDaoJpa(EntityManager em) {
    this.em = em;
  }

  @Override
  public Author save(Author author) {
    if (author.getId() <= 0) {
      em.persist(author);
      return author;
    } else {
      return em.merge(author);
    }
  }

  @Override
  public List<Author> getAll() {
    TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
    return query.getResultList();
  }

  @Override
  public Author getById(long id) {
    return em.find(Author.class, id);
  }

  private static class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
      long id = rs.getLong("id");
      String name = rs.getString("author_name");
      return new Author(id, name);
    }
  }
}
