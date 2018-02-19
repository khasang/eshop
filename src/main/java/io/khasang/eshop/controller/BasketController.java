package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Basket;
import io.khasang.eshop.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Since this is a basket, we should be able to:
 * delete the element (method - deleteProductInBasket)
 * adding an element (method - addProductInBasket)
 * product update (method - updateProductInBasket)
 * getting goods in the basket, for a specific user (method - getGoodsByUser)
 * clearing the user's basket (method - clearGoods)
 * Since some of the operations in the basket itself, after it is executed we need to see the updated cart.
 */
@Controller
@RequestMapping(value = "/basket")
public class BasketController {

    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Basket> addProductInBasket(@RequestBody Basket basket) {
        return basketService.getGoodsByUser(basketService.add(basket).getUser());
    }

    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    @ResponseBody
    public List<Basket> getGoodsByUser(@PathVariable(value = "user") String user) {
        return basketService.getGoodsByUser(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Basket> updateProductInBasket(@RequestBody Basket basket) {
        return getGoodsByUser(basketService.updateProduct(basket).getUser());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Basket deleteProductInBasket(@RequestBody Basket basket) {
        return basketService.delete(basket);
    }

    @RequestMapping(value = "/clear/{user}", method = RequestMethod.DELETE)
    @ResponseBody
    public void clearGoods(@PathVariable(value = "user") String user) {
        basketService.clear(user);
    }
}
