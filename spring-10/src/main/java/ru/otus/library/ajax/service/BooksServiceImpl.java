package ru.otus.library.ajax.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.ajax.config.Default;
import ru.otus.library.ajax.dao.BooksDao;
import ru.otus.library.ajax.exception.NotFoundException;
import ru.otus.library.ajax.models.Author;
import ru.otus.library.ajax.models.Book;
import ru.otus.library.ajax.models.Genre;

/**
 * Implementation of service responsible for operations with Books.
 */
@Service
@AllArgsConstructor
public class BooksServiceImpl implements BooksService {
  private final BooksDao booksDao;

  private final AuthorsService authorsService;
  private final GenresService genreService;

  @Override
  public List<Book> findBooks() {
    return booksDao.findAll();
  }

  @Override
  public Book getBook(String bookId) {
    return booksDao.findById(bookId)
                   .orElseThrow(() -> new NotFoundException("Book is not found"));
  }

  @Transactional
  @Override
  public String addBook(String title,
                      int publicationYear,
                      String authorId,
                      String genreId) {
    Author author = authorsService.getAuthorById(authorId);
    Genre genre = genreService.getGenreById(genreId);

    Book book = new Book(null, title, publicationYear, author, genre, List.of());
    Book resultingBook = booksDao.save(book);
    return resultingBook.getId();
  }

  @Transactional
  @Override
  public void updateBook(
          String id,
          String title,
          int publicationYear,
          String authorId,
          String genreId
  ) {
    Optional<Book> book = booksDao.findById(id);
    if (book.isEmpty()) {
      throw new NotFoundException("No book found");
    }
    Book.BookBuilder builder = book.get().getBuilder();

    if (!Objects.equals(title, Default.NONE)) {
      builder.title(title);
    }

    if (publicationYear != Default.ZERO) {
      builder.publicationYear(publicationYear);
    }

    if (!Objects.equals(authorId, Default.NONE)) {
      Author author = authorsService.getAuthorById(authorId);
      builder.author(author);
    }

    if (!Objects.equals(genreId, Default.NONE)) {
      Genre genre = genreService.getGenreById(genreId);
      builder.genre(genre);
    }

    Book resultingBook = builder.build();
    booksDao.save(resultingBook);
  }
}
