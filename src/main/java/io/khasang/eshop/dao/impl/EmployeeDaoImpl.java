package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.EmployeeDao;
import io.khasang.eshop.entity.Employee;

public class EmployeeDaoImpl extends BasicDaoImpl<Employee> implements EmployeeDao {
    public EmployeeDaoImpl(Class<Employee> entityClass) {
        super(entityClass);
    }
}
