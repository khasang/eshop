package io.khasang.eshop.service.impl;

import io.khasang.eshop.dao.OrderDao;
import io.khasang.eshop.entity.Order;
import io.khasang.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getList();
    }

    @Override
    public Order addOrder(Order order) {
        return orderDao.add(order);
    }

    @Override
    public Order getOrderById(long id) {
        return orderDao.getById(id);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderDao.update(order);
    }

    @Override
    public Order deleteOrder(long id) {
        return orderDao.delete(getOrderById(id));
    }
}
