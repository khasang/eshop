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
    //Делаем автосвязку с объектом который находится в облаке бинов
    @Autowired
    private Cat catInterface;

    @Autowired
    private CreateTable createTable;

    @RequestMapping("/")
    public String startPage() {
        return "hello";
    }

    //Указываем путь страницы, в данном случае http://localhost:8080/
    @RequestMapping("/cat")
    @ResponseBody
    public Cat CatPage() {
        return catInterface;
    }

    //запускаем методы после перехода по ссылке
    @RequestMapping("/admin/create")
    @ResponseBody
    public String createTable() {
        return createTable.createTableCat() + " | " +
                createTable.createTableColors() + " | " +
                createTable.addCatsInTable() + " | " +
                createTable.addColorsInTable() + " | " +
                createTable.updateCats() + " | " +
                createTable.deleteCats() + " | " +
                createTable.selectCats() + " | " +
                createTable.selectCats1();
    }

    @RequestMapping("/admin")
    public String getSecurePage() {
        return "admin";
    }

    @RequestMapping("user/**")
    public String getUserPage() {
        return "user";
    }

    //разобрать
    @ResponseBody
    @RequestMapping(value = ("/password/{password}"), method = RequestMethod.GET)
    public String getEncryptedPassword(@PathVariable("password") String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
