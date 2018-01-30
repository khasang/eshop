package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Phone;
import io.khasang.eshop.servise.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/phone")
public class PhoneController {
    private final PhoneService phoneService;

    @Autowired
    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Phone> allPhone(){
        return phoneService.allPhone();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Phone getPhoneById(@PathVariable (value = "id") String id) {
        return phoneService.getById(Long.parseLong(id));
    }
}
