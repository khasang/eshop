package io.khasang.eshop.service;

import io.khasang.eshop.entity.Book;

import java.util.List;


public interface BookService {
    /**
     * method for receiving all books from DB
     *
     * @return List of all books
     **/
    List<Book> getALLBooks();

    /**
     * method for receiving specify book by id
     *
     * @param id = books's id
     * @return book by id
     **/
    Book getById(long id);

    /**
     * method for add book
     *
     * @param book = new book
     * @return created book
     **/
    Book addBook(Book book);

    /**
     * method for delete specify book by id
     *
     * @param id = book's id
     * @return book by id
     **/
    Book delete(long id);

    /**
     * method for update specify book by id
     *
     * @param book = new book
     * @return updated book
     **/
    Book update(Book book);
}
