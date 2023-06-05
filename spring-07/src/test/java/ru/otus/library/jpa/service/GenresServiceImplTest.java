package ru.otus.library.jpa.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.library.jpa.dao.GenresDao;
import ru.otus.library.jpa.models.Genre;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(classes = GenresServiceImpl.class)
public class GenresServiceImplTest {
  private final Genre GENRE = new Genre(1L, "Test Genre");

  @MockBean
  private GenresDao genresDao;

  @Autowired
  private GenresServiceImpl authorsService;

  @DisplayName("Prints genres in ioService")
  @Test
  void shouldPrintGenres() {
    List<Genre> genres = List.of(GENRE);
    doReturn(genres).when(genresDao).getAll();

    List<Genre> result = authorsService.findGenres();
    assertThat(result).containsExactlyInAnyOrderElementsOf(genres);
  }
}
