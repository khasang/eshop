package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.UserDao;
import io.khasang.eshop.entity.User;

public class UserDaoImpl extends BasicDaoImpl<User> implements UserDao {
    public UserDaoImpl(Class<User> entityClass) {
        super(entityClass);
    }
}
