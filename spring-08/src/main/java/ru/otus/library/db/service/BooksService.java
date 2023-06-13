package ru.otus.library.db.service;

import ru.otus.library.db.exception.NotFoundException;
import ru.otus.library.db.models.Book;
import java.util.List;

/**
 * Service responsible for operations with Books.
 */
public interface BooksService {
  List<Book> findBooks();

  Book getBook(long bookId) throws NotFoundException;

  void addBook(String title,
               int publicationYear,
               long authorId,
               long genreId) throws NotFoundException;

  void updateBook(
          long id,
          String title,
          int publicationYear,
          long authorId,
          long genreId
  ) throws NotFoundException;
}
