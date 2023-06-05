package ru.otus.library.jpa.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.jpa.models.Book;

/**
 * CRUD operations with Books.
 */
public interface BooksDao extends JpaRepository<Book, Long> {

  Optional<Book> findById(long id);

  @EntityGraph(attributePaths = {"genre", "author"})
  List<Book> findAll();
}
