package ru.otus.library.orm.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Book data class.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
  private Long id;
  private String title;
  private int publicationYear;
  private Author author;
  private Genre genre;
}
