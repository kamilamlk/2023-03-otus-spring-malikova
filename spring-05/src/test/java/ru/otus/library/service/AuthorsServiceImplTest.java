package ru.otus.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.library.dao.AuthorsDao;
import ru.otus.library.models.Author;
import java.util.List;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = AuthorsServiceImpl.class)
public class AuthorsServiceImplTest {
  private final Author AUTHOR = new Author(1L, "Test Author");

  @MockBean
  private AuthorsDao authorsDao;
  @MockBean
  private IoService ioService;

  @Autowired
  private AuthorsServiceImpl authorsService;

  @DisplayName("Prints authors in ioService")
  @Test
  void shouldPrintAuthors() {
    List<Author> authors = List.of(AUTHOR);
    doReturn(authors).when(authorsDao).getAll();
    doNothing().when(ioService).writeAuthor(AUTHOR);
    authorsService.showAuthors();
    verify(ioService, times(1)).writeAuthor(AUTHOR);
  }
}
