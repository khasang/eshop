package io.khasang.eshop.dao;

import io.khasang.eshop.entity.Basket;

import java.util.List;


public interface BasketDao extends BasicDao<Basket> {

    /**
     * method for receiving all goods by user name from DB
     *@param user = name user's
     * @return List of all goods by user name
     */
    List<Basket> getGoodsByUser(String user);

    /**
     * method for delete specify product in Basket
     *
     * @param product = product for delete
     * @return List goods in Basket
     */
    List<Basket> deleteByProduct(Basket product);

}
