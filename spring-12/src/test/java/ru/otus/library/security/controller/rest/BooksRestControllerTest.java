package ru.otus.library.security.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.library.security.controller.dto.AuthorDto;
import ru.otus.library.security.controller.dto.BookDto;
import ru.otus.library.security.controller.dto.GenreDto;
import ru.otus.library.security.models.Author;
import ru.otus.library.security.models.Book;
import ru.otus.library.security.models.Genre;
import ru.otus.library.security.service.BooksService;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@ContextConfiguration(classes = BooksRestController.class)
public class BooksRestControllerTest {
  @Autowired
  private MockMvc mvc;
  @Autowired
  private ObjectMapper mapper;

  @MockBean
  private BooksService booksService;

  @Test
  @DisplayName("Should return books")
  void testFindBooks() throws Exception {
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

    mvc.perform(get("/api/book"))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
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
    mvc.perform(get("/api/book/1"))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(expectedBook)));
  }

  @Test
  @DisplayName("Should edit book")
  void testEditBook() throws Exception {
    String bookId = "1";

    BookDto book = new BookDto();
    book.setId(bookId);
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
            post("/api/book")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(book))
            )
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(new BookDto("1"))));
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
            post("/api/book")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(book))
            )
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(new BookDto("1"))));
  }
}
