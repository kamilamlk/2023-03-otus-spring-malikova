package ru.otus.library.flux.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.library.flux.controller.dto.AuthorDto;
import ru.otus.library.flux.models.Author;
import ru.otus.library.flux.service.AuthorsService;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@ContextConfiguration(classes = AuthorsController.class)
public class AuthorsControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private AuthorsService authorsService;

  @Test
  @DisplayName("Should show edit page")
  public void testShowEditPage() throws Exception {
    Author author = new Author("1", "Author");
    doReturn(author).when(authorsService).getAuthorById(anyString());

    AuthorDto expectedResult = AuthorDto.toDto(author);

    mvc.perform(get("/author/1"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("author", expectedResult));
  }
}
