package ru.otus.library.mvc.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.library.mvc.models.Genre;

/**
 * Genre dto for controller.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenreDto {
  private String id;
  private String name;

  public static GenreDto toDto(Genre genre) {
    return new GenreDto(genre.getId(), genre.getName());
  }
}
