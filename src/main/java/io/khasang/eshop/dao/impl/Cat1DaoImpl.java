package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.Cat1Dao;
import io.khasang.eshop.entity.Cat1;

public class Cat1DaoImpl extends BasicDaoImpl<Cat1> implements Cat1Dao {

    public Cat1DaoImpl(Class<Cat1> entityClass) {
        super(entityClass);
    }
}
