package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.OrderDao;
import io.khasang.eshop.entity.Order;

public class OrderDaoImpl extends BasicDaoImpl<Order> implements OrderDao {

    public OrderDaoImpl(Class<Order> entityClass) {
        super(entityClass);
    }
}
