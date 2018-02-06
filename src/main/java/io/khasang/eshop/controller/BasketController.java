package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Basket;
import io.khasang.eshop.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public void addBasket(@RequestBody Basket basket){
        basketService.addBasket(basket);
        getAllBasket();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Basket> getAllBasket(){
        return basketService.getAllBasket();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public void deleteBasket(@RequestParam(value = "{id}") String id){
        basketService.delete(Long.parseLong(id));
        getAllBasket();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public void updateBasket(@RequestBody Basket basket){
        basketService.updateBasket(basket);
        getAllBasket();
    }


}
