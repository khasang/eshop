package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.GoodDao;
import io.khasang.eshop.entity.Good;

public class GoodDaoImpl extends BasicDaoImpl<Good> implements GoodDao {

    public GoodDaoImpl(Class<Good> entityClass) {
        super(entityClass);
    }
}
