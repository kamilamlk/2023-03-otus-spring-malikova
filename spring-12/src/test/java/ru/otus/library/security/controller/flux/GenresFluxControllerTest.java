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
import ru.otus.library.security.controller.dto.GenreDto;
import ru.otus.library.security.dao.flux.GenresFluxDao;
import ru.otus.library.security.models.Genre;
import static org.mockito.Mockito.doReturn;

@WebFluxTest()
@ContextConfiguration(classes = GenresFluxController.class)
public class GenresFluxControllerTest {
  @Autowired
  private WebTestClient webClient;

  @MockBean
  private GenresFluxDao fluxDao;

  @Test
  @DisplayName("Should return genres")
  void testFindGenres() {
    List<Genre> genres = List.of(
            new Genre("1", "Genre 1"),
            new Genre("2", "Genre 2"),
            new Genre("3", "Genre 3")
    );

    doReturn(Flux.just(genres.toArray())).when(fluxDao).findAll();

    webClient
            .get().uri("/api/flux/genre")
            .accept(MediaType.TEXT_EVENT_STREAM)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBodyList(GenreDto.class);
  }
}
