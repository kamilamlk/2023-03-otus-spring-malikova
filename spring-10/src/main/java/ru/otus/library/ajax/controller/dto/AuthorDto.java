package ru.otus.library.ajax.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.library.ajax.models.Author;

/**
 * Author dto for controller.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDto {
  private String id;
  private String name;

  public static AuthorDto toDto(Author author) {
    return new AuthorDto(author.getId(), author.getName());
  }
}
