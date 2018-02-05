package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.CatDao;
import io.khasang.eshop.entity.Cat;

public class CatDaoImpl extends BasicDaoImpl<Cat> implements CatDao {

    public CatDaoImpl(Class<Cat> entityClass) {
        super(entityClass);
    }
}
