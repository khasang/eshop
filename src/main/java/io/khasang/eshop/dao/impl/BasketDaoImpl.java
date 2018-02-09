package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.BasketDao;
import io.khasang.eshop.entity.Basket;

import javax.persistence.Query;
import java.util.List;

public class BasketDaoImpl extends BasicDaoImpl<Basket> implements BasketDao {
    public BasketDaoImpl(Class<Basket> entityClass) {
        super(entityClass);
    }

    /**
     * Method of obtaining the user's product
     *
     * @param user whose basket we will receive
     * @return list of goods of the users
     */
    @Override
    public List<Basket> getGoodsByUser(String user) {
        Query query = getSession().createQuery("SELECT b FROM Basket as b WHERE b.user = :userName");
        query.setParameter("userName", user);
        return query.getResultList();
    }

    /**
     * Delete product in basket
     *
     * @param product product than we remove from the basket
     * @return topical list goods
     */
    public List<Basket> deleteByProduct(Basket product) {
        getSession().remove(product);
        return getGoodsByUser(product.getUser());
    }

    /**
     * Add new product
     * If the product is in the basket, update its quantity,
     * if not add new ones.
     *
     * @param product = new product
     * @return new product or update product
     */
    @Override
    public Basket add(Basket product) {
        List<Basket> basket = getGoodsByUser(product.getUser());
        for (Basket productInBasket : basket) {
            if (productInBasket.equals(product)) {
                productInBasket.setQuantity(productInBasket.getQuantity() + product.getQuantity());
                update(productInBasket);
                return productInBasket;
            }
        }
        getSession().save(product);
        return product;
    }

}
