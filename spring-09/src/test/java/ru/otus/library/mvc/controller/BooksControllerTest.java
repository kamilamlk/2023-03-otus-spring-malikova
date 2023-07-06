package ru.otus.library.mvc.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.library.mvc.controller.dto.AuthorDto;
import ru.otus.library.mvc.controller.dto.BookDto;
import ru.otus.library.mvc.controller.dto.GenreDto;
import ru.otus.library.mvc.models.Author;
import ru.otus.library.mvc.models.Book;
import ru.otus.library.mvc.models.Genre;
import ru.otus.library.mvc.service.AuthorsService;
import ru.otus.library.mvc.service.BooksService;
import ru.otus.library.mvc.service.GenresService;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@ContextConfiguration(classes = BooksController.class)
public class BooksControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private BooksService booksService;

  @MockBean
  private AuthorsService authorsService;
  @MockBean
  private GenresService genresService;

  @Test
  @DisplayName("should return list of books")
  public void testFindBooks() throws Exception {
    List<Book> books = List.of(
            new Book("1", "Title", 1990,
                    new Author("1", "Author 1"),
                    new Genre("1", "Genre 1"),
                    List.of()
            ),
            new Book("2", "Title", 1990,
                    new Author("2", "Author 2"),
                    new Genre("2", "Genre 2"),
                    List.of()
            ),
            new Book("3", "Title", 1990,
                    new Author("3", "Author 3"),
                    new Genre("3", "Genre 3"),
                    List.of()
            )
    );
    doReturn(books).when(booksService).findBooks();

    List<BookDto> expectedResult = books.stream()
                                           .map(BookDto::toDto)
                                           .collect(Collectors.toList());

    mvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("books", expectedResult));
  }

  @Test
  @DisplayName("should return a book")
  void testGetBook() throws Exception {
    Book book = new Book(
            "1", "Title", 1990,
            new Author("1", "Author 1"),
            new Genre("1", "Genre 1"),
            List.of()
    );
    doReturn(book).when(booksService).getBook(anyString());
    BookDto expectedBook = BookDto.toDto(book);
    mvc.perform(get("/book/1"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("book", expectedBook));
  }

  @Test
  @DisplayName("Should add book")
  void testAddBook() throws Exception {
    String bookId = "1";

    BookDto book = new BookDto();
    book.setTitle("New book");
    book.setPublicationYear(2000);
    book.setAuthor(new AuthorDto("1", ""));
    book.setGenre(new GenreDto("1", ""));

    doReturn(bookId).when(booksService).addBook(
            anyString(),
            anyInt(),
            anyString(),
            anyString()
    );

    mvc.perform(
            post("/book")
                    .param("title", book.getTitle())
                    .param("publicationYear", String.valueOf(book.getPublicationYear()))
                    .param("author.id", book.getAuthor().getId())
                    .param("genre.id", book.getGenre().getId())
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/book/1"));
  }

  @Test
  @DisplayName("Should add book")
  void testEditBook() throws Exception {
    BookDto book = new BookDto();
    book.setTitle("New book");
    book.setPublicationYear(2000);
    book.setAuthor(new AuthorDto("1", ""));
    book.setGenre(new GenreDto("1", ""));

    doNothing().when(booksService).updateBook(
            anyString(),
            anyString(),
            anyInt(),
            anyString(),
            anyString()
    );

    mvc.perform(
                    post("/book/1/edit")
                            .param("title", book.getTitle())
                            .param("publicationYear", String.valueOf(book.getPublicationYear()))
                            .param("author.id", book.getAuthor().getId())
                            .param("genre.id", book.getGenre().getId())
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/book/1"));
  }
}
