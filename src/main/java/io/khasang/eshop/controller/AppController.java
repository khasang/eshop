package io.khasang.eshop.controller;

import io.khasang.eshop.model.Cat;
import io.khasang.eshop.model.CatInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {

    @Autowired
    //@Value("Barsik")

    private CatInterface catInterface;

    // http://localhost:8080/
    @RequestMapping("/")
    @ResponseBody
    public CatInterface helloPage(Model model){
       catInterface.setName("Мурзик");
       model.addAttribute("WORLD");
        return catInterface;

    }
}
