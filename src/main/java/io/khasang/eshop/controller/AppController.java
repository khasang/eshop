package io.khasang.eshop.controller;

import io.khasang.eshop.model.CatInterface;
import io.khasang.eshop.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {

    @Autowired
    private CatInterface catInterface;
    @Autowired
    private CreateTable createTable;

    // http://localhost:8080/
    @RequestMapping("/")
    @ResponseBody
    public CatInterface helloPage(Model model) {
        catInterface.setName("Murzik");
        model.addAttribute("name", "World");
        return catInterface;
    }

    @RequestMapping("/create")
    @ResponseBody
    public String createTable(){
        return createTable.createTableStatus();
    }

}
