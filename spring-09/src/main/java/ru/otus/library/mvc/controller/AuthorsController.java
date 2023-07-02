package ru.otus.library.mvc.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.library.mvc.controller.dto.AuthorDto;
import ru.otus.library.mvc.service.AuthorsService;

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
  public String findBooks(Model model) {
    List<AuthorDto> authors = authorsService.findAuthors()
                                      .stream()
                                      .map(AuthorDto::toDto).toList();
    model.addAttribute("authors", authors);
    return "authors";
  }

  /**
   * Adds a new author.
   */
  @PostMapping("/author")
  public String addAuthor(@ModelAttribute AuthorDto author) {
    authorsService.addAuthor(author.getName());
    return "redirect:/authors";
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

  /**
   * Edits author.
   */
  @PostMapping("/author/{id}")
  public String editComment(@PathVariable("id") String authorId,
                            @ModelAttribute AuthorDto authorDto) {
    authorsService.updateAuthor(authorId, authorDto.getName());
    return "redirect:/authors";
  }
}
