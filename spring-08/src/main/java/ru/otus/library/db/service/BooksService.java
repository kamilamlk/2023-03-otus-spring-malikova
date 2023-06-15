package ru.otus.library.db.service;

import java.util.List;
import ru.otus.library.db.exception.NotFoundException;
import ru.otus.library.db.models.Book;

/**
 * Service responsible for operations with Books.
 */
public interface BooksService {
  List<Book> findBooks();

  Book getBook(String bookId) throws NotFoundException;

  void addBook(String title,
               int publicationYear,
               String authorId,
               String genreId) throws NotFoundException;

  void updateBook(
          String id,
          String title,
          int publicationYear,
          String authorId,
          String genreId
  ) throws NotFoundException;
}
