package ru.otus.library.flux.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.library.flux.controller.dto.AuthorDto;
import ru.otus.library.flux.service.AuthorsService;

/**
 * Controller for operations with author.
 */
@Controller
@AllArgsConstructor
public class AuthorsController {
  private final AuthorsService authorsService;

  /**
   * Returns all authors.
   */
  @GetMapping("/authors")
  public String findBooks() {
    return "authors";
  }

  /**
   * Shows edit page.
   */
  @GetMapping("/author/{id}")
  public String showAuthorEditPage(@PathVariable("id") String authorId,
                                   Model model) {
    AuthorDto author = AuthorDto.toDto(authorsService.getAuthorById(authorId));
    model.addAttribute("author", author);
    return "edit-author";
  }
}
