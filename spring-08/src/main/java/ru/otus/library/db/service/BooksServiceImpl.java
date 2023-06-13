package ru.otus.library.db.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.db.config.Default;
import ru.otus.library.db.dao.BooksDao;
import ru.otus.library.db.exception.NotFoundException;
import ru.otus.library.db.models.Author;
import ru.otus.library.db.models.Book;
import ru.otus.library.db.models.Genre;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
  public Book getBook(long bookId) {
    return booksDao.findById(bookId)
                   .orElseThrow(() -> new NotFoundException("Book is not found"));
  }

  @Transactional
  @Override
  public void addBook(String title,
                      int publicationYear,
                      long authorId,
                      long genreId) {
    Author author = authorsService.getAuthorById(authorId);
    Genre genre = genreService.getGenreById(genreId);

    Book book = new Book(Default.ZERO, title, publicationYear, author, genre, List.of());
    booksDao.save(book);
  }

  @Transactional
  @Override
  public void updateBook(
          long id,
          String title,
          int publicationYear,
          long authorId,
          long genreId
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

    if (authorId != Default.ZERO) {
      Author author = authorsService.getAuthorById(authorId);
      builder.author(author);
    }

    if (genreId != Default.ZERO) {
      Genre genre = genreService.getGenreById(genreId);
      builder.genre(genre);
    }

    Book resultingBook = builder.build();
    booksDao.save(resultingBook);
  }
}
