package io.khasang.eshop.controller;


import io.khasang.eshop.entity.Anonymous;
import io.khasang.eshop.service.AnonymousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@RequestMapping("/anonymous")
public class AnonymousController {
    private final AnonymousService anonymousService;
    @Autowired
    public AnonymousController(AnonymousService anonymousService){
        this.anonymousService = anonymousService;
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Anonymous> getAllAnonymous() {
        return anonymousService.getAllAnonymous();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Anonymous addAnonymous(@RequestBody Anonymous anonymous){
        return anonymousService.addAnonymous(anonymous);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Anonymous updateAnonymous(@RequestBody Anonymous anonymous){
        return anonymousService.updateAnonymous(anonymous);
    }

    @RequestMapping(value = "/patch", method = RequestMethod.PATCH, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Anonymous patchAnonymous(@RequestBody Anonymous anonymous){
        return anonymousService.patchAnonymous(anonymous);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Anonymous deleteAnonymous(@RequestParam(value = "id") String id) {
        return anonymousService.delete(Long.parseLong(id));
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Anonymous getAnonymousById(@PathVariable(value = "id") String id) {
        return anonymousService.getById(Long.parseLong(id));
    }
}
