package io.khasang.eshop.controller;

import io.khasang.eshop.model.Cat;
import io.khasang.eshop.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {

    @Autowired
    private Cat catInterface;

    @Autowired
    private CreateTable createTable;

    // http://localhost:8080/
    @RequestMapping("/")
    @ResponseBody
    public Cat halloPage(Model model) {
//        model.addAttribute("name", "World");
        return catInterface;
    }

    //запускаем методы после перехода по ссылке
    @RequestMapping("/create")
    @ResponseBody
    public String createTable(){
        return createTable.createTableCats() + " " +
                createTable.createTableColors() + " " +
                createTable.addCatsInTable() + " " +
                createTable.addColorsInTable() + " " +
                createTable.updateCats() + " " +
                createTable.deletCats() + " " +
                createTable.selectCats() + " " +
                createTable.selectCats1();
    }

    @RequestMapping("/admin/page")
    public String getSecurePage(){
        return "admin";
    }

    @RequestMapping("/user/**")
    public String getUserPage(){
        return "user";
    }

    //шифруем папрот по параметру из ссылки
    @ResponseBody
    @RequestMapping(value = "/password/{password}", method = RequestMethod.GET)
    public String getEncryptedPassword(@PathVariable("password") String password){
        return new BCryptPasswordEncoder().encode(password);
    }
}
