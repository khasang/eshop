package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Author1;
import io.khasang.eshop.service.Author1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@Controller
@RequestMapping(value = "/author1", method = RequestMethod.GET)
public class Author1Controller {
    private final Author1Service author1Service;
    @Autowired
    public Author1Controller(Author1Service author1Service){
        this.author1Service = author1Service;
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Author1> getAllAuthors(){
        return author1Service.getAllAuthors();
    }
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Author1 getAuthorById(@PathVariable(value = "id")String id){
        return author1Service.getById(Long.parseLong(id));
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Author1 addAuthor(@RequestBody Author1 author1){
        return author1Service.addAuthor(author1);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Author1 deleteAuthor(@RequestParam(value = "id")String id){
        return author1Service.deleteAuthor(Long.parseLong(id));
    }
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Author1 updateAuthor(@RequestBody Author1 author1){
        return author1Service.updateAuthor(author1);
    }

    @RequestMapping(value = "/patch", method = RequestMethod.PATCH, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Author1 patchAuthor(@RequestBody Author1 author1){
        return author1Service.patchAuthor(author1);
    }
}
