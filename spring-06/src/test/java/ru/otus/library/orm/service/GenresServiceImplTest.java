package ru.otus.library.orm.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.library.orm.dao.GenresDao;
import ru.otus.library.orm.models.Genre;
import java.util.List;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = GenresServiceImpl.class)
public class GenresServiceImplTest {
  private final Genre GENRE = new Genre(1L, "Test Genre");

  @MockBean
  private GenresDao genresDao;
  @MockBean
  private IoService ioService;

  @Autowired
  private GenresServiceImpl authorsService;

  @DisplayName("Prints genres in ioService")
  @Test
  void shouldPrintGenres() {
    List<Genre> genres = List.of(GENRE);
    doReturn(genres).when(genresDao).getAll();
    doNothing().when(ioService).writeGenre(GENRE);
    authorsService.showGenres();
    verify(ioService, times(1)).writeGenre(GENRE);
  }
}
