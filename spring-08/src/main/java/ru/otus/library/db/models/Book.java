package ru.otus.library.db.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Book data class.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "book-comments-entity-graph",
        attributeNodes = {@NamedAttributeNode("comments")})
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "publication_year")
  private int publicationYear;

  @ManyToOne(targetEntity = Author.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "author_id")
  private Author author;

  @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "genre_id")
  private Genre genre;

  @OneToMany(mappedBy = "book", orphanRemoval = true)
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
