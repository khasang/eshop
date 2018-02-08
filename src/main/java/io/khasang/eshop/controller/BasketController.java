package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Basket;
import io.khasang.eshop.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class BasketController {

    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public void addBasket(@RequestBody Basket basket){
        basketService.addBasket(basket);
        getAllBasket(basket.getUser());
    }

    @RequestMapping(value = "/basket/{user}", method = RequestMethod.GET)
    @ResponseBody
    public List<Basket> getAllBasket(@PathVariable(value = "user") String user){
        return basketService.getGoodsByUser(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public void deleteBasket(@RequestParam(value = "{id}") String id){
        basketService.delete(Long.parseLong(id));
//        getAllBasket();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public void updateBasket(@RequestBody Basket basket){
        basketService.updateBasket(basket);
        getAllBasket(basket.getUser());
    }


}
