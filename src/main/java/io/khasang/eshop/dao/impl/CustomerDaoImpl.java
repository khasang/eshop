package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.CustomerDao;
import io.khasang.eshop.entity.Customer;

public class CustomerDaoImpl extends BasicDaoImpl<Customer> implements CustomerDao {

    public CustomerDaoImpl(Class<Customer> entityClass) {
        super(entityClass);
    }
}
