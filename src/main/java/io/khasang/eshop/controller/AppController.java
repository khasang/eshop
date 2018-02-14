package io.khasang.eshop.controller;

import io.khasang.eshop.model.CreateTable;
import io.khasang.eshop.util.CheckText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;

@Controller
public class AppController {
    //Автосвязка с объектом из облака бинов
    @Autowired
    private CreateTable createTable;

    @Autowired
    private CheckText checkText;
    //Указываем путь страницы, в данном случае http://localhost:8080/
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String helloPage() {
        return "OK!";
    }

    //запускаем методы после перехода по ссылке
    @RequestMapping(value = "/admin/create", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getSecurePage() {
        return "admin";
    }

    @RequestMapping(value = "/user/**", method = RequestMethod.GET)
    public String getUserPage() {
        return "user";
    }

    @RequestMapping(value = ("/password/{password}"), method = RequestMethod.GET)
    @ResponseBody
    public String getEncryptedPassword(@PathVariable("password") String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @RequestMapping(value = ("/text/{text}"), method = RequestMethod.GET)
    @ResponseBody
    public String checkText(@PathVariable("text") String text) throws MalformedURLException {
        return checkText.checkWord(text);
    }
}
