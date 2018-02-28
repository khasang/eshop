package io.khasang.eshop.service;

import io.khasang.eshop.DTO.BookDTO;
import io.khasang.eshop.entity.Book;

import java.util.List;

public interface BookService {

    List<BookDTO> getAllBook();

    Book addBook(Book book);

    Book getBook(long id);

    Book deleteBook(long id);
}
