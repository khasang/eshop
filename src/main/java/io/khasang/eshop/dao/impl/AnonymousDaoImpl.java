package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.AnonymousDao;
import io.khasang.eshop.entity.Anonymous;

public class AnonymousDaoImpl extends BasicDaoImpl<Anonymous> implements AnonymousDao {
    public AnonymousDaoImpl(Class<Anonymous> entityClass) {
        super(entityClass);
    }
}
