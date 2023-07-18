package ru.otus.library.flux.controller.flux;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.flux.controller.dto.BookDto;
import ru.otus.library.flux.dao.flux.BooksFluxDao;
import ru.otus.library.flux.dao.flux.CommentsFluxDao;
import ru.otus.library.flux.models.Book;

/**
 * Flux controller.
 */
@RequiredArgsConstructor
@RestController
public class BooksFluxController {
  private final BooksFluxDao booksDao;
  private final CommentsFluxDao commentsDao;

  /**
   * Returns books.
   */
  @GetMapping(path = "/api/flux/book")
  public Flux<BookDto> findBooks() {
    return booksDao.findAll()
                   .map(BookDto::toDto);
  }

  /**
   * Returns the book.
   */
  @GetMapping(path = "/api/flux/book/{id}")
  public Mono<BookDto> getBook(@PathVariable("id") String bookId) {
    return booksDao.findById(bookId).map(BookDto::toDto);
  }

  /**
   * Saves a book.
   */
  @PostMapping(path = "/api/flux/book")
  public Mono<BookDto> saveBook(@RequestBody BookDto book) {
    return commentsDao.findAllByBook_Id(book.getId())
                   .collectList()
                   .map(c -> {
                     Book b = book.toBook();
                     b.setComments(c);
                     return b;
                   }).flatMap(booksDao::save)
                   .map(BookDto::toDto);
  }
}
