package io.khasang.eshop.dao;

import io.khasang.eshop.entity.Basket;

import java.util.List;


public interface BasketDao extends BasicDao<Basket> {

    /**
     * method for receiving goods in basket by user name from DB
     *@param user = user name
     * @return List of goods by user name
     */
    List<Basket> getGoodsByUser(String user);
}
