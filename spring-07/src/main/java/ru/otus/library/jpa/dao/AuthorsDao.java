package ru.otus.library.jpa.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.library.jpa.models.Author;

/**
 * CRUD operations with Author.
 */
public interface AuthorsDao extends JpaRepository<Author, Long> {
  List<Author> findAll();

  Optional<Author> findById(long id);

  @Modifying
  @Query("update Author a set a.name = :name where a.id = :id")
  void update(@Param("id") long id, @Param("name") String name);

  void deleteById(long id);
}
