package ru.otus.library.security.controller.rest;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.library.security.controller.dto.BookDto;
import ru.otus.library.security.models.Book;
import ru.otus.library.security.service.BooksService;

/**
 * Rest controller.
 */
@RequiredArgsConstructor
@RestController
public class BooksRestController {
  private final BooksService booksService;

  /**
   * Returns books.
   */
  @GetMapping("/api/book")
  public List<BookDto> findBooks() {
    return booksService.findBooks()
                   .stream()
                   .map(BookDto::toDto)
                   .collect(Collectors.toList());
  }

  /**
   * Returns the book.
   */
  @GetMapping("/api/book/{id}")
  public BookDto getBook(@PathVariable("id") String bookId) {
    Book book = booksService.getBook(bookId);
    return BookDto.toDto(book);
  }

  /**
   * Saves a book.
   */
  @PostMapping("/api/book")
  @ResponseBody
  public BookDto saveBook(@RequestBody BookDto book) {
    String id;
    if (book.getId() == null) {
      id = booksService.addBook(
              book.getTitle(),
              book.getPublicationYear(),
              book.getAuthor().getId(),
              book.getGenre().getId()
      );
    } else {
      booksService.updateBook(
              book.getId(),
              book.getTitle(),
              book.getPublicationYear(),
              book.getAuthor().getId(),
              book.getGenre().getId()
      );
      id = book.getId();
    }
    return new BookDto(id);
  }
}
