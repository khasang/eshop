package io.khasang.eshop.controller;

import io.khasang.eshop.model.Cat;
import io.khasang.eshop.model.CatInterface;
import io.khasang.eshop.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    //@Value("Barsik")
    private CatInterface catInterface;
    @Autowired
    private CreateTable createTable;


    // http://localhost:8080/
    @RequestMapping("/")
    @ResponseBody
    public CatInterface helloPage(Model model) {
        catInterface.setName("Мурзик");
        model.addAttribute("WORLD");
        return catInterface;
    }

    @RequestMapping("/create")
    @ResponseBody
    public String createTable() {
        return createTable.createTableStatus();
    }

//    @RequestMapping("/exe")
//    @ResponseBody
//    public int createInquiry(){
//        return createTable.createInquiryStatus();
//    }
//
//    @RequestMapping("/exe1")
//    @ResponseBody
//    public List createInquiry1(){
//        return createTable.createInquiryStatus1();
//    }

    @RequestMapping("/admin/page")
   // @ResponseBody
        public  String getSecurePage(){
         return "admin";
        }
     @RequestMapping("/user/**")
     public  String getUserPage(){
        return "user";
     }

     @ResponseBody
     @RequestMapping(value = {"/password/{password}"}, method = RequestMethod.GET)
     public String getEncryptedPassword(@PathVariable("password")String password){
        return new BCryptPasswordEncoder().encode(password);
     }

}
