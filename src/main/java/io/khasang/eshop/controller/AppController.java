package io.khasang.eshop.controller;

import io.khasang.eshop.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {

    @Autowired
    private Cat catInterface;


    // http://localhost:8080/
    @RequestMapping("/")
    @ResponseBody
    public Cat halloPage(Model model) {
//        model.addAttribute("name", "World");
        return catInterface;
    }
}
