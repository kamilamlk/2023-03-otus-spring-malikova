package ru.otus.library.security.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Book data class.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {
  @Id
  private String id;

  private String title;

  private int publicationYear;

  private Author author;

  private Genre genre;

  @DBRef(lazy = true)
  private List<Comment> comments;

  /**
   * Return builder with predefined default values.
   */
  public BookBuilder getBuilder() {
    return Book.builder()
                   .id(this.id)
                   .title(this.title)
                   .publicationYear(this.publicationYear)
                   .author(this.author)
                   .genre(this.genre)
                   .comments(this.comments);
  }
}
