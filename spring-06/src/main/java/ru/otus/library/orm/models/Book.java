package ru.otus.library.orm.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Book data class.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
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
}