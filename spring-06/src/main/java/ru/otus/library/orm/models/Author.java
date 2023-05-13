package ru.otus.library.orm.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author data class.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
  private Long id;
  private String name;
}
