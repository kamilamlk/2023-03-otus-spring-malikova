package ru.otus.library.security.controller.rest;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.library.security.controller.dto.GenreDto;
import ru.otus.library.security.service.GenresService;

/**
 * Rest controller.
 */
@RequiredArgsConstructor
@RestController
public class GenresRestController {
  private final GenresService genresService;

  /**
   * Returns genres.
   */
  @GetMapping("/api/genres")
  public List<GenreDto> findBooks() {
    return genresService.findGenres()
                   .stream()
                   .map(GenreDto::toDto).toList();
  }
}
