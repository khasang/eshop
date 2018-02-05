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

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Book getById(@PathVariable(value = "id") String id){
        return bookService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Book> getAll(){
        return bookService.getAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Book deleteBook(@RequestParam(value = "id") String id) {
        return bookService.deleteBook(Long.parseLong(id));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

}
