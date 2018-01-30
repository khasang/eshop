package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.PhoneDao;
import io.khasang.eshop.entity.Phone;

public class PhoneDaoImpl extends BasicDaoImpl<Phone> implements PhoneDao {
    public PhoneDaoImpl(Class<Phone> entityClass) {
        super(entityClass);
    }
}
