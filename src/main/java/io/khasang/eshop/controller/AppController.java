package io.khasang.eshop.controller;

import io.khasang.eshop.model.Cat;
import io.khasang.eshop.model.CatInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {

    @Autowired
    private CatInterface catInterface;


    // http://localhost:8080/
    @RequestMapping("/")
    @ResponseBody
    public CatInterface halloPage(Model model){
//        model.addAttribute("name", "World");
        return catInterface;
    }
}
