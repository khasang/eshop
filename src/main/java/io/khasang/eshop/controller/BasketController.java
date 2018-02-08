package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Basket;
import io.khasang.eshop.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/basket")
public class BasketController {

    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Basket addBasket(@RequestBody Basket basket){
        return basketService.addBasket(basket);
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    @ResponseBody
    public List<Basket> getAllBasket(@RequestParam(value = "user") String user){
        return basketService.getGoodsByUser(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Basket> deleteBasket(@RequestBody Basket basket){
       return basketService.delete(basket);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public void updateBasket(@RequestBody Basket basket){
        basketService.updateBasket(basket);
    }


}
