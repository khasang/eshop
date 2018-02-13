package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Cat1;
import io.khasang.eshop.service.Cat1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@Controller
@RequestMapping(value = "/cat1", method = RequestMethod.GET)
public class Cat1Controller {

    private final Cat1Service cat1Service;
    @Autowired
    public Cat1Controller(Cat1Service cat1Service) {
        this.cat1Service = cat1Service;
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Cat1> getAllCats(){
        return cat1Service.getAllCats();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Cat1 getCatById(@PathVariable(value = "id")String id){
        return cat1Service.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Cat1 addCat(@RequestBody Cat1 cat1){
        return cat1Service.addCat(cat1);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Cat1 deleteCat(@RequestParam(value = "id")String id){
        return cat1Service.deleteCat(Long.parseLong(id));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Cat1 updateCat(@RequestBody Cat1 cat1){
        return cat1Service.updateCat(cat1);
    }

    @RequestMapping(value = "/patch", method = RequestMethod.PATCH, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Cat1 patchCat(@RequestBody Cat1 cat1){
        return cat1Service.patchCat(cat1);
    }
}
