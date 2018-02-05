package io.khasang.eshop.service.impl;

import io.khasang.eshop.dao.CustomerDao;
import io.khasang.eshop.entity.Customer;
import io.khasang.eshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> getAll() {
        return customerDao.getList();
    }

    @Override
    public Customer getById(Long id) {
        return customerDao.getById(id);
    }
}
