package io.khasang.eshop.controller;
import io.khasang.eshop.model.*;
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
    private CrudOperations crudOperations;

    @Autowired
    private CheckText checkText;

    @RequestMapping("/")
    @ResponseBody
    public String helloPage() {
        return "It's working!";
    }

    @RequestMapping("/admin/page")
    public String getSecurePage() {
        return "admin";
    }

    @RequestMapping("/user/**")
    public String getUserPage() {
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

    @RequestMapping("/create")
    @ResponseBody
    public String createTable() {
        return crudOperations.createTableStatus();
    }

    @RequestMapping("/read")
    @ResponseBody
    public String selectFromTable() {
        return crudOperations.read(1);
    }

    @RequestMapping("/update")
    @ResponseBody
    public String insertEntry() {
        return crudOperations.update();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteEntry() {
        return crudOperations.delete();
    }

}
