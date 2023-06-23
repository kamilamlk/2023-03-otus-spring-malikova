package ru.otus.library.db.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.db.models.Book;

/**
 * CRUD operations with Books.
 */
public interface BooksDao extends MongoRepository<Book, String> {
}