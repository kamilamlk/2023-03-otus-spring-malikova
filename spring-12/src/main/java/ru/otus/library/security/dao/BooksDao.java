package ru.otus.library.security.dao;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.security.models.Book;

/**
 * CRUD operations with Books.
 */
public interface BooksDao extends MongoRepository<Book, String> {
  List<Book> findAllByAuthor_Id(String authorId);
}
