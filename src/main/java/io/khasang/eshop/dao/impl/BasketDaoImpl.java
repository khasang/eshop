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
     * Method of obtaining the user's basket
     * @param user whose basket we will receive
     * @return entire user cart
     */
    @Override
    public List<Basket> getGoodsByUser(String user) {
        Query query = getSession().createQuery("SELECT b FROM Basket as b WHERE b.user = :userName");
        query.setParameter("userName", user);
        return query.getResultList();
    }

    /**
     * Delete basket
     * @param basket = basket for delete
     * @return topical list baskets
     */
    public List<Basket> deleteByEntity(Basket basket){
        getSession().remove(basket);
        return getGoodsByUser(basket.getUser());
    }

    /**
     * Add new basket
     * @param entity = new basket
     * @return new basket
     */
    @Override
    public Basket add(Basket entity) {
         Basket basket = getSession().createQuery("SELECT b FROM Basket b where goods = :goods and user = :userName", entityClass)
                 .setParameter("userName", entity.getUser())
                 .setParameter("goods", entity.getGoods())
                 .getSingleResult();

        if (basket == null){
            basket.setQuantity(basket.getQuantity() + entity.getQuantity());
            getSession().update(basket);
            return basket;
        } else {
            getSession().save(entity);
        }
            return entity;
    }

}
