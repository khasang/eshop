package io.khasang.eshop.service;

import io.khasang.eshop.entity.Basket;

import java.util.List;

public interface BasketService {

    /**
     * method for receiving all Basket's from DB
     *
     * @return List of all Basket's
     */
    List<Basket> getAllBasket();

    /**
     * method for receiving specify Basket by id
     *
     * @param id = Basket's id
     * @return Basket by id
     */
    Basket getById(long id);

    /**
     * method for receiving all Basket's by user name from DB
     *
     * @return List of all Basket's by user name
     */
    List<Basket> getGoodsByUser(String user);

    /**
     * method for add basket
     *
     * @param basket = new Basket
     * @return created Basket
     */
    Basket addBasket(Basket basket);

    /**
     * method for delete specify cat by id
     *
     * @param basket = basket
     * @return List basket
     */
    List<Basket> delete(Basket basket);

    /**
     * method for update cat
     *
     * @param cat = updated cat
     * @return updated cat
     */
    Basket updateBasket(Basket cat);
}
