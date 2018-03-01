package io.khasang.eshop.controller;

import io.khasang.eshop.DTO.BookDTO;
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
    public List<BookDTO> getAllBook() {
        return bookService.getAllBook();
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BookDTO getBook(@PathVariable(value = "id") String id) {
        return bookService.getBook(Long.parseLong(id));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public BookDTO deleteBook(@PathVariable(value = "id") String id) {
        return bookService.deleteBook(Long.parseLong(id));
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String bookMenu() {
        return "book";
    }
}
