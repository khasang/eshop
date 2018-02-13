package io.khasang.eshop.servise;

import io.khasang.eshop.entity.Book;

public interface BookService {
    void addBook();

    Book getBook(int id);
}
