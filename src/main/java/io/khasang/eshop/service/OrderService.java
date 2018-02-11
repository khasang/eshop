package io.khasang.eshop.service;

import io.khasang.eshop.entity.Order;

import java.util.List;

public interface OrderService {

    /**
     * method for receiving all orders from DB
     *
     * @return List of all orders
     */
    List<Order> getAllOrders();

    /**
     * method for add order
     *
     * @param order = new order
     * @return created order
     */
    Order addOrder(Order order);

    /**
     * method for receiving specify order by id
     *
     * @param id = order's id
     * @return order by id
     */
    Order getOrderById(long id);

    /**
     * method for update order
     *
     * @param order = updated order
     * @return updated order
     */
    Order updateOrder(Order order);

    /**
     * method for delete specify order by id
     *
     * @param id = order's id
     * @return order by id
     */
    Order deleteOrder(long id);
}
