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
    @Autowired
    private CreateTable createTable;
    @Autowired
    private CheckText checkText;

    // http://localhost:8080/
    @RequestMapping("/")
    @ResponseBody
    public String helloPage() {
        return "OK!";
    }

    @RequestMapping("/create")
    @ResponseBody
    public String createTable(){
        return createTable.createTableStatus();
    }

    @RequestMapping(value = "/admin/page")
    public String getSecurePage(){
        return "admin";
    }

    @RequestMapping("/user/**")
    public String getUserPAge(){
        return "user";
    }

    @ResponseBody
    @RequestMapping(value = {"/password/{password}"}, method = RequestMethod.GET)
    public String getEncryptedPassword(@PathVariable("password") String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @ResponseBody
    @RequestMapping(value = {"/text/{text}"}, method = RequestMethod.GET)
    public String checkText(@PathVariable("text") String text) throws MalformedURLException {
        return checkText.checkWord(text);
    }

}
