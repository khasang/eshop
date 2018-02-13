package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.AuthorDao;
import io.khasang.eshop.entity.Author;

public class AuthorDaoImpl extends BasicDaoImpl<Author> implements AuthorDao {
    public AuthorDaoImpl(Class<Author> entityClass) {
        super(entityClass);
    }
}
