package ru.otus.library.security.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.library.security.controller.dto.GenreDto;
import ru.otus.library.security.service.GenresService;

/**
 * Controller for operations with genre.
 */
@Controller
@AllArgsConstructor
public class GenresController {
  private final GenresService genresService;

  /**
   * Returns genres.
   */
  @GetMapping("/genres")
  public String findGenres(Model model) {
    List<GenreDto> genres = genresService.findGenres()
                                      .stream()
                                      .map(GenreDto::toDto).toList();
    model.addAttribute("genres", genres);
    return "genres";
  }
}
