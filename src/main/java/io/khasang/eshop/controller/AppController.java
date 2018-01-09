package io.khasang.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    @RequestMapping("/")
    public String helloPage(Model model) {
        model.addAttribute("name", "World");
        return "hello";
    }
}
