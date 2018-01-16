package io.khasang.eshop.controller;

import io.khasang.eshop.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
    @Autowired
    private CreateTable createTable;

    @RequestMapping("/")
    public String helloPage(Model model) {
        model.addAttribute("name", "Russia");
        return "hello";
    }

    @RequestMapping("/create")
    @ResponseBody
    public String createTable() {
        return createTable.createTableStatus();
    }

}
