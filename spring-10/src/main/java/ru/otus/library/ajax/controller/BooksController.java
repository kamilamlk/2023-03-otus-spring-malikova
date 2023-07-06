package ru.otus.library.ajax.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.library.ajax.controller.dto.AuthorDto;
import ru.otus.library.ajax.controller.dto.BookDto;
import ru.otus.library.ajax.controller.dto.GenreDto;
import ru.otus.library.ajax.models.Book;
import ru.otus.library.ajax.service.AuthorsService;
import ru.otus.library.ajax.service.BooksService;
import ru.otus.library.ajax.service.GenresService;

/**
 * Controller for operations with book.
 */
@AllArgsConstructor
@Controller
public class BooksController {
  private final BooksService booksService;
  private final AuthorsService authorsService;
  private final GenresService genresService;

  /**
   * Returns all books.
   */
  @GetMapping("/")
  public String findBooks(Model model) {
    List<BookDto> books = booksService.findBooks()
                                  .stream()
                                  .map(BookDto::toDto)
                                  .collect(Collectors.toList());
    model.addAttribute("books", books);
    return "index";
  }

  /**
   * Returns one book.
   */
  @GetMapping(value = "/book/{id}")
  public String getBook(Model model, @PathVariable("id") String bookId) {
    Book book = booksService.getBook(bookId);
    BookDto bookDto = BookDto.toDto(book);
    model.addAttribute("book", bookDto);
    return "book";
  }

  /**
   * Adds a new book.
   */
  @PostMapping("/book")
  public String addBook(@ModelAttribute BookDto book) {
    String bookId = booksService.addBook(
            book.getTitle(),
            book.getPublicationYear(),
            book.getAuthor().getId(),
            book.getGenre().getId()
    );
    return String.format("redirect:/book/%s", bookId);
  }

  /**
   * Shows new book page.
   */
  @GetMapping("/book")
  public String showNewBookPage(Model model) {
    List<AuthorDto> authors = authorsService.findAuthors()
                                      .stream().map(AuthorDto::toDto)
                                      .toList();

    List<GenreDto> genres = genresService.findGenres()
                                    .stream().map(GenreDto::toDto)
                                    .toList();
    model.addAttribute("authors", authors);
    model.addAttribute("genres", genres);
    return "new-book";
  }

  /**
   * Shows edit page.
   */
  @GetMapping("/book/{id}/edit")
  public String showEditBookPage(Model model, @PathVariable("id") String bookId) {
    BookDto book = BookDto.toDto(booksService.getBook(bookId));
    model.addAttribute("book", book);

    List<AuthorDto> authors = authorsService.findAuthors()
                                      .stream().map(AuthorDto::toDto)
                                      .toList();
    List<GenreDto> genres = genresService.findGenres()
                                    .stream().map(GenreDto::toDto)
                                    .toList();

    model.addAttribute("authors", authors);
    model.addAttribute("genres", genres);

    return "edit-book";
  }

  /**
   * Updates book's information.
   */
  @PostMapping("/book/{id}/edit")
  public String editBook(@PathVariable("id") String bookId, @ModelAttribute BookDto book) {
    booksService.updateBook(
            bookId,
            book.getTitle(),
            book.getPublicationYear(),
            book.getAuthor().getId(),
            book.getGenre().getId()
    );
    return String.format("redirect:/book/%s", bookId);
  }
}
