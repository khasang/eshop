package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Author;
import io.khasang.eshop.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@Controller
@RequestMapping(value = "/author", method = RequestMethod.GET)
public class AuthorController {
    private final AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Author getAuthorById(@PathVariable(value = "id")String id){
        return authorService.getById(Long.parseLong(id));
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Author addAuthor(@RequestBody Author author){
        return authorService.addAuthor(author);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Author deleteAuthor(@RequestParam(value = "id")String id){
        return authorService.deleteAuthor(Long.parseLong(id));
    }
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Author updateAuthor(@RequestBody Author author){
        return authorService.updateAuthor(author);
    }

    @RequestMapping(value = "/patch", method = RequestMethod.PATCH, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Author patchAuthor(@RequestBody Author author){
        return authorService.patchAuthor(author);
    }
}
