package ru.otus.library.ajax.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Author data class.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authors")
public class Author {
  @Id
  private String id;

  private String name;

  public Author(String authorName) {
    this.name = authorName;
  }
}
