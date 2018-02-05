package io.khasang.eshop.service;

import io.khasang.eshop.entity.Customer;

import java.util.List;

public interface CustomerService {
    /**
     * method for receiving all customers
     *
     * @return list of all customers
     */
    List<Customer> getAll();

    /**
     * method for receiving distinct customer by id
     * @param id = customer's id
     * @return customer by id
     */
    Customer getById(Long id);
}
