package ru.otus.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Genre data class.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
  private long id;
  private String name;
}
