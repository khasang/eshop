package io.khasang.eshop.service;

import io.khasang.eshop.dto.BookDTO;
import io.khasang.eshop.entity.Book;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();

    BookDTO getBookById(long id);

    Book addBook(Book book);

    Book deleteBook(long id);

    Book updateBook(Book book);
}
