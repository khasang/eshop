package io.khasang.eshop.service;

import io.khasang.eshop.entity.Book;

public interface BookService {
    Book addBook(Book book);

    Book getBook(long id);

    Book deleteBook(long id);
}
