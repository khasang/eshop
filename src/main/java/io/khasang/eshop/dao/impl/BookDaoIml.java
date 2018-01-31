package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.BookDao;
import io.khasang.eshop.entity.Book;

public class BookDaoIml extends BasicDaoImpl<Book> implements BookDao {
    public BookDaoIml(Class<Book> entityClass){
        super(entityClass);
    }

}
