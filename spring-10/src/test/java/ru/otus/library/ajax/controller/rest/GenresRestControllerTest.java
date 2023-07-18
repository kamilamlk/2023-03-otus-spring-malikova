package ru.otus.library.ajax.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.library.ajax.controller.dto.GenreDto;
import ru.otus.library.ajax.models.Genre;
import ru.otus.library.ajax.service.GenresService;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@ContextConfiguration(classes = GenresRestController.class)
public class GenresRestControllerTest {
  @Autowired
  private MockMvc mvc;
  @Autowired
  private ObjectMapper mapper;
  @MockBean
  private GenresService genresService;

  @Test
  @DisplayName("Should return genres")
  void testFindGenres() throws Exception {
    List<Genre> genres = List.of(
            new Genre("1", "Genre 1"),
            new Genre("2", "Genre 2"),
            new Genre("3", "Genre 3")
    );

    doReturn(genres).when(genresService).findGenres();
    List<GenreDto> expectedResult = genres.stream()
                                            .map(GenreDto::toDto)
                                            .toList();

    mvc.perform(get("/api/genres"))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
  }
}