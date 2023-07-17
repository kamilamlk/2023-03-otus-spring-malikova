package ru.otus.library.flux.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Genre data class.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "genres")
public class Genre {
  @Id
  private String id;

  private String name;
}
