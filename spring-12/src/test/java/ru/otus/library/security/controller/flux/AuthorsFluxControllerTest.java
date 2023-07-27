package ru.otus.library.security.controller.flux;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.library.security.controller.dto.AuthorDto;
import ru.otus.library.security.dao.flux.AuthorsFluxDao;
import ru.otus.library.security.models.Author;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@WebFluxTest()
@ContextConfiguration(classes = AuthorsFluxController.class)
public class AuthorsFluxControllerTest {
  @Autowired
  private WebTestClient webClient;

  @MockBean
  private AuthorsFluxDao authorsDao;

  @Test
  @DisplayName("Should return authors")
  void testFindAuthors() throws Exception {
    List<Author> authors = List.of(
            new Author("1", "Author 1"),
            new Author("2", "Author 2"),
            new Author("3", "Author 3")
    );
    doReturn(Flux.just(authors.toArray())).when(authorsDao).findAll();

    webClient
            .get().uri("/api/flux/author")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBodyList(AuthorDto.class);
  }

  @Test
  @DisplayName("Should add author")
  void testAddAuthor() {
    Author author = new Author(null, "Author 1");

    doReturn(Mono.just(author)).when(authorsDao).save(any(Author.class));

    webClient
            .post().uri("/api/flux/author")
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(AuthorDto.toDto(author))
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(AuthorDto.class);
  }

  @Test
  @DisplayName("Should return the author")
  public void testGetAuthor() throws Exception {
    Author author = new Author("1", "Author");
    doReturn(Mono.just(author)).when(authorsDao).findById(anyString());

    AuthorDto expectedResult = AuthorDto.toDto(author);

    Flux<AuthorDto> response = webClient
                                       .get().uri("/api/flux/author/1")
                                       .accept(MediaType.APPLICATION_JSON)
                                       .exchange()
                                       .expectStatus()
                                       .isOk()
                                       .returnResult(AuthorDto.class)
                                       .getResponseBody();

    StepVerifier
            .create(response)
            .assertNext(a -> assertEquals(expectedResult, a))
            .expectComplete()
            .verify();
  }
}
