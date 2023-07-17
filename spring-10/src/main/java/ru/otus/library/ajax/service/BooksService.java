package ru.otus.library.ajax.service;

import java.util.List;
import ru.otus.library.ajax.exception.NotFoundException;
import ru.otus.library.ajax.models.Book;

/**
 * Service responsible for operations with Books.
 */
public interface BooksService {
  List<Book> findBooks();

  Book getBook(String bookId) throws NotFoundException;

  String addBook(String title,
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
