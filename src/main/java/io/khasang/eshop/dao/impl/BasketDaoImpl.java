package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.BasketDao;
import io.khasang.eshop.entity.Basket;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BasketDaoImpl extends BasicDaoImpl<Basket> implements BasketDao {
    public BasketDaoImpl(Class<Basket> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Basket> getList() {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Basket> criteriaQuery = builder.createQuery(Basket.class);
        Root<Basket> root = criteriaQuery.from(Basket.class);

        criteriaQuery.select(root);
        return getSession().createQuery(criteriaQuery).list();
    }

    @Override
    public Basket getById(long id) {
        return super.getById(id);
    }

    @Override
    public Basket add(Basket entity) {
        return super.add(entity);
    }

    @Override
    public Basket update(Basket entity) {
        return super.update(entity);
    }

    @Override
    public Basket delete(Basket entity) {
        return super.delete(entity);
    }
}
