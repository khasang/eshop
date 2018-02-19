package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Good;
import io.khasang.eshop.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/good")
public class GoodController {
    private final GoodService goodService;

    @Autowired
    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Good addGood(@RequestBody Good good) {
        return goodService.addGood(good);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Good> getAllGoods() {
        return goodService.getAllGoods();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Good updateGood(@RequestBody Good good){
        return goodService.updateGood(good);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Good deleteGood(@RequestParam(value = "id") String id) {
        return goodService.delete(Long.parseLong(id));
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Good getGoodById(@PathVariable(value = "id") String id) {
        return goodService.getById(Long.parseLong(id));
    }
}
