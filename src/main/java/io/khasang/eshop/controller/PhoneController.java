package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Phone;
import io.khasang.eshop.servise.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public List<Phone> allPhone() {
        return phoneService.allPhone();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Phone getPhoneById(@PathVariable(value = "id") String id) {
        return phoneService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Phone addPhone(@RequestBody Phone phone) {
        return phoneService.addPhone(phone);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Phone deletePhone(@RequestParam(value = "id") String id) {
        return phoneService.deletePhone(Long.parseLong(id));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Phone updatePhone(@RequestBody Phone phone) {
        return phoneService.updatePhone(phone);
    }
}
