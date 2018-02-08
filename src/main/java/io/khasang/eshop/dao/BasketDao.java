package io.khasang.eshop.dao;

import io.khasang.eshop.entity.Basket;

import java.util.List;


public interface BasketDao extends BasicDao<Basket> {

    List<Basket> getGoodsByUser(String user);

    /**
     * method for delete specify basket
     *
     * @param basket = entity for delete
     * @return List basket
     */
    List<Basket> deleteByEntity(Basket basket);

}
