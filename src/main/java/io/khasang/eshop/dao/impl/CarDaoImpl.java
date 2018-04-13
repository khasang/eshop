package io.khasang.eshop.dao.impl;


import io.khasang.eshop.dao.CarDao;
import io.khasang.eshop.entity.Car;

public class CarDaoImpl extends BasicDaoImpl<Car> implements CarDao {

    public CarDaoImpl(Class<Car> entityClass) {
        super(entityClass);
    }
}

