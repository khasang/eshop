package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Author;
import io.khasang.eshop.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Author addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Author getAuthorById(@RequestParam(value = "id") String id) {
        return authorService.getAuthorById(Long.parseLong(id));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Author update(@RequestBody Author author) {
        return authorService.updateAuthor(author);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Author deleteAuthor(@RequestParam(value = "id") String id) {
        return authorService.deleteAuthor(Long.parseLong(id));
    }
}
