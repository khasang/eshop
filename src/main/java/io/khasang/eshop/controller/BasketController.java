package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Basket;
import io.khasang.eshop.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Т.к. это корзина, мы должны иметь возможность:
 * удалять элемент (method - deleteProductInBasket)
 * добавление элемента (method - addProductInBasket)
 * обновление товара (method - updateProductInBasket)
 * получение товаров находящихся в корзине, для конкретного пользователя (method - getGoodsByUser)
 * Т.к. некоторые оперции проводятся в самой корзине, после её выпоплнения мы должны видеть обновленную карзину.
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
