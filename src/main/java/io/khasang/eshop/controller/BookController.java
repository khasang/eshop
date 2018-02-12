package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Author;
import io.khasang.eshop.entity.Book;
import io.khasang.eshop.servise.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/book")
public class BookController {

    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public void addBook(@RequestBody Book book){
//        Book book = new Book("Психология");
//        Author author1 = new Author("Ерик Берн");
//        Author author2 = new Author("Зигмунд Фрейд");
//        book.addAuthor(author1);
//        book.addAuthor(author2);
        bookService.addBook(book);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Book getBook(@PathVariable (value = "id") String id){
        return bookService.getBook(Integer.parseInt(id));
    }
}
