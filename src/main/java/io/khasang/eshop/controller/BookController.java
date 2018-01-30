package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Book;
import io.khasang.eshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Book getBookById(@PathVariable(value = "id") String id) {
        return bookService.getBookById(Long.parseLong(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Book deleteBookById(@RequestParam(value = "id") String id) {
        return bookService.deleteBook(Long.parseLong(id));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

}
