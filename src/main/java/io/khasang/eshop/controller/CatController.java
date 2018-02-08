package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Cat;
import io.khasang.eshop.servise.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cats")
//localhost:8080/cats/**
public class CatController {
    private final CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Cat> getAllCats() {
        return catService.getAllCats();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Cat getCatById(@PathVariable(value = "id") String id) {
        return catService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Cat addCatById(@RequestBody Cat cat) {
        return catService.addCat(cat);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Cat deleteCat(@RequestParam(value = "id") String id) {
        return catService.delete(Long.parseLong(id));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Cat updateCat(@RequestBody Cat cat) {
        return catService.updateCat(cat);
    }
}
