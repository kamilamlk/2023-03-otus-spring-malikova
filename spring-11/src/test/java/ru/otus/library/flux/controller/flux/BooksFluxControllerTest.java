package ru.otus.library.flux.controller.flux;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.library.flux.controller.dto.AuthorDto;
import ru.otus.library.flux.controller.dto.BookDto;
import ru.otus.library.flux.controller.dto.GenreDto;
import ru.otus.library.flux.dao.flux.BooksFluxDao;
import ru.otus.library.flux.models.Author;
import ru.otus.library.flux.models.Book;
import ru.otus.library.flux.models.Genre;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;


@WebFluxTest()
@ContextConfiguration(classes = BooksFluxController.class)
public class BooksFluxControllerTest {
  @Autowired
  private WebTestClient webClient;

  @MockBean
  private BooksFluxDao booksDao;

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
    doReturn(Flux.just(books.toArray())).when(booksDao).findAll();

    List<BookDto> expectedResult = books.stream().map(BookDto::toDto).toList();

    FluxExchangeResult<BookDto> result = webClient
                                                 .get().uri("/api/flux/book")
                                                 .exchange()
                                                 .expectStatus()
                                                 .isOk()
                                                 .returnResult(BookDto.class);
    StepVerifier.create(result.getResponseBody())
            .assertNext(b -> assertThat(b).matches(r -> expectedResult.get(0).equals(r)))
            .assertNext(b -> assertThat(b).matches(r -> expectedResult.get(1).equals(r)))
            .assertNext(b -> assertThat(b).matches(r -> expectedResult.get(2).equals(r)))
            .expectComplete()
            .verify();
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
    doReturn(Mono.just(book)).when(booksDao).findById(anyString());

    BookDto expectedBook = BookDto.toDto(book);

    FluxExchangeResult<BookDto> result = webClient.get()
                                                 .uri("/api/flux/book/1")
                                                 .exchange()
                                                 .expectStatus()
                                                 .isOk()
                                                 .returnResult(BookDto.class);
    StepVerifier.create(result.getResponseBody())
            .assertNext(b -> assertThat(b).matches(expectedBook::equals))
            .expectComplete()
            .verify();
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
    book.setComments(List.of());

    doReturn(Mono.just(book.toBook())).when(booksDao).save(any(Mono.class));

    FluxExchangeResult<BookDto> result = webClient.post()
                                                 .uri("/api/flux/book")
                                                 .accept(MediaType.APPLICATION_JSON)
                                                 .bodyValue(book)
                                                 .exchange()
                                                 .expectStatus()
                                                 .isOk()
                                                 .returnResult(BookDto.class);

    StepVerifier.create(result.getResponseBody())
            .assertNext(b -> assertThat(b)
                                     .matches(book::equals)
                                     .matches(r -> r.getId().equals(bookId)))
            .expectComplete()
            .verify();
  }

  @Test
  @DisplayName("Should add book")
  void testAddBook() throws Exception {
    String bookId = "1";

    BookDto book = new BookDto();
    book.setId(bookId);
    book.setTitle("New book");
    book.setPublicationYear(2000);
    book.setAuthor(new AuthorDto("1", ""));
    book.setGenre(new GenreDto("1", ""));
    book.setComments(List.of());

    doReturn(Mono.just(book.toBook())).when(booksDao).save(any(Mono.class));

    FluxExchangeResult<BookDto> result = webClient.post()
                                                 .uri("/api/flux/book")
                                                 .accept(MediaType.APPLICATION_JSON)
                                                 .bodyValue(book)
                                                 .exchange()
                                                 .expectStatus()
                                                 .isOk()
                                                 .returnResult(BookDto.class);

    StepVerifier.create(result.getResponseBody())
            .assertNext(b -> assertThat(b).matches(book::equals)
                                     .matches(r -> r.getId().equals(bookId)))
            .expectComplete()
            .verify();
  }
}
