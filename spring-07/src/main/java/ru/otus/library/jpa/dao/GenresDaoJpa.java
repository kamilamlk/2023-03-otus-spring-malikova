package ru.otus.library.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository;
import ru.otus.library.jpa.models.Genre;

/**
 * Implementation of CRUD operations with Genres.
 */
@Repository
public class GenresDaoJpa implements GenresDao {
  @PersistenceContext
  private final EntityManager em;

  public GenresDaoJpa(EntityManager em) {
    this.em = em;
  }

  @Override
  public Genre save(Genre genre) {
    if (genre.getId() <= 0) {
      em.persist(genre);
      return genre;
    } else {
      return em.merge(genre);
    }
  }

  @Override
  public List<Genre> getAll() {
    TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
    return query.getResultList();
  }

  @Override
  public Genre getById(long id) {
    return em.find(Genre.class, id);
  }

  @Override
  public void update(Genre genre) {
    em.merge(genre);
  }

  @Override
  public void delete(Genre genre) {
    em.remove(genre);
  }
}
