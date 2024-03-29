package ru.otus.library.flux.controller.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.library.flux.models.Book;

/**
 * Book dto for controller.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {
  private String id;
  private String title;
  private int publicationYear;
  private AuthorDto author;
  private GenreDto genre;
  private List<CommentDto> comments;

  public BookDto(String id) {
    this.id = id;
  }

  /**
   * Creates DTO.
   */
  public static BookDto toDto(Book book) {
    return new BookDto(
            book.getId(),
            book.getTitle(),
            book.getPublicationYear(),
            AuthorDto.toDto(book.getAuthor()),
            GenreDto.toDto(book.getGenre()),
            book.getComments()
                    .stream()
                    .map(CommentDto::toDto)
                    .toList()
    );
  }

  /**
   * Return Book instance.
   */
  public Book toBook() {
    return new Book(
            id, title, publicationYear,
            author.toAuthor(), genre.toGenre(),
            List.of()
    );
  }
}
