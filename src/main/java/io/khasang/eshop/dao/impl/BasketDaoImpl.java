package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.BasketDao;
import io.khasang.eshop.entity.Basket;

import javax.persistence.Query;

public class BasketDaoImpl extends BasicDaoImpl<Basket> implements BasketDao {
    public BasketDaoImpl(Class<Basket> entityClass) {
        super(entityClass);
    }

    @Override
    public Basket add(Basket entity) {
        Query query = getSession().createQuery("SELECT b FROM Basket as b WHERE b.user = :userName and b.goods = 'Juse'");
        getSession().save(entity);
        return entity;

    }
}
