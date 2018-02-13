package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.BookDao;
import io.khasang.eshop.entity.Book;
import org.hibernate.Session;

public class BookDaoImpl extends BasicDaoImpl<Book> implements BookDao {
    public BookDaoImpl(Class<Book> entityClass) {
        super(entityClass);
    }

    @Override
    public Book add(Book book) {
        Session session1 = getSession();
        session1.persist(book);
        session1.flush();
        return book;
    }
}
