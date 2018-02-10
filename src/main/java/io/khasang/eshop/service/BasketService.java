package io.khasang.eshop.service;

import io.khasang.eshop.entity.Basket;

import java.util.List;

public interface BasketService {

    /**
     * method for receiving all goods by user name from DB
     *@param user = name user's
     * @return List of all goods by user name
     */
    List<Basket> getGoodsByUser(String user);

    /**
     * method for add product
     *
     * @param product = new product in Basket
     * @return created Basket
     */
    Basket add(Basket product);

    /**
     * method for delete specify product by id
     *
     * @param product = product in Basket
     * @return delete product
     */
    Basket delete(Basket product);

    /**
     * method for update product in Basket
     *
     * @param product = updated product
     * @return updated product
     */
    Basket updateProduct(Basket product);

    /**
     * Clear goods in basket
     * @param user = user name
     */
    void clearBasket(String user);
}
