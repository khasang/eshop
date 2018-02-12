package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Author;
import io.khasang.eshop.entity.Book;
import org.junit.Test;

public class BookIntegrationTest {

    @Test
    public void newBook() {
        Book book = new Book("Психология");
        Author author1 = new Author("Ерик Берн");
        Author author2 = new Author("Зигмунд Фрейд");
        book.addAuthor(author1);
        book.addAuthor(author2);
        Book book1 = new Book("Люди которые играют в игры");
        author2.addBook(book1);
    }
}
