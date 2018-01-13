package io.khasang.eshop.controller;

import io.khasang.eshop.model.Cat;
import io.khasang.eshop.model.Dog;
import io.khasang.eshop.model.PetInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
    @Autowired
    @Qualifier("myau")
    PetInterface petInterface;
    @RequestMapping("/")
    @ResponseBody
    public PetInterface helloPage(Model model) {
        model.addAttribute("name", "World");
        if(petInterface instanceof Cat)
        petInterface.setName("Barsik");
        if(petInterface instanceof Dog)
        petInterface.setName("Muhtar");
        return petInterface;
    }
}
