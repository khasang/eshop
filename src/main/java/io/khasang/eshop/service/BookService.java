package io.khasang.eshop.service;

import io.khasang.eshop.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {
    /**
     * method for receiving book by id
     *
     * @param id = book's id
     * @return distinct book by id
     */
    Book getById(long id);

    /**
     * method for receiving list of all books
     *
      * @return list of all books
     */
    List<Book> getAll();

    /**
     * method for adding new book
     *
     * @return added book
     */
    Book addBook(Book book);

    /**
     * method for deleting distinct book by id
     *
     * @param  id = book's id
     * @return deleted book
     */
    Book deleteBook(long id);

    /**
     * method for updating book
     *
     * @param  book = updated book
     * @return updated book
     */
    Book updateBook(Book book);
}
