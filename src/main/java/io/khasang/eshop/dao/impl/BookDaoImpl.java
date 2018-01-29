package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.BookDao;
import io.khasang.eshop.entity.Book;

public class BookDaoImpl extends BasicDaoImpl<Book> implements BookDao{
    //private Class<Book> entityClass;

    public BookDaoImpl(Class<Book> entityClass) {
        super(entityClass);
    }
}
