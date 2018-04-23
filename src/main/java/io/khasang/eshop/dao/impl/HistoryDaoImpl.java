package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.HistoryDao;
import io.khasang.eshop.entity.History;

public class HistoryDaoImpl extends BasicDaoImpl<History> implements HistoryDao {
    public HistoryDaoImpl(Class<History> entityClass) {
        super(entityClass);
    }
}
