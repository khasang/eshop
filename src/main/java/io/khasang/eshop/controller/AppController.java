package io.khasang.eshop.controller;

import io.khasang.eshop.model.*;
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
    private CreateTable createTable;
    @Autowired
    private SelectRecord selectRecord;
    @Autowired
    private InsertRow insertRow;
    @Autowired
    private UpdateTable updateTable;
    @Autowired
    private DeleteRecord deleteRecord;

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

    @RequestMapping("/select")
    @ResponseBody
    public String selectRecord() {
        return selectRecord.selectRecordStatus();
    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insertRow() {
        return insertRow.insertRow();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String updateTable() {
        return updateTable.update();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleterecord() {
        return deleteRecord.deleteRecord();
    }

    @RequestMapping("/admin/page")
    public String getSecurePage() {
        return "admin";
    }

    @RequestMapping("/user/**")
    public String getUserPage() {
        return "user";
    }

    @RequestMapping(value = "/password/{password}", method = RequestMethod.GET)
    @ResponseBody
    public String getEncriptedPassword(@PathVariable("password") String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
