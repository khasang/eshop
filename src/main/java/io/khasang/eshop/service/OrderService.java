package io.khasang.eshop.service;

import io.khasang.eshop.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    Order addOrder(Order order);

    Order getOrderById(long id);

    Order updateOrder(Order order);

    Order deleteOrder(long id);
}
