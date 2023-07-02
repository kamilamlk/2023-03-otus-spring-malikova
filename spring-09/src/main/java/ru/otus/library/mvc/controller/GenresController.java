package ru.otus.library.mvc.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.library.mvc.controller.dto.GenreDto;
import ru.otus.library.mvc.service.GenresService;

/**
 * Controller for operations with genre.
 */
@Controller
@AllArgsConstructor
public class GenresController {
  private final GenresService genresService;

  @GetMapping("/genres")
  public String findBooks(Model model) {
    List<GenreDto> genres = genresService.findGenres()
                                      .stream()
                                      .map(GenreDto::toDto).toList();
    model.addAttribute("genres", genres);
    return "genres";
  }
}
