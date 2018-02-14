package io.khasang.eshop.dao.impl;

import io.khasang.eshop.dao.BookDao;
import io.khasang.eshop.entity.Book;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.Transaction;

public class BookDaoImpl extends BasicDaoImpl<Book> implements BookDao {
    public BookDaoImpl(Class<Book> entityClass) {
        super(entityClass);
    }

    @Override
    public Book add(Book book) {
        Session sessions = getSession();
        sessions.persist(book);
        sessions.flush();
        return book;
    }

    @Override
    public Book delete(Book book) {
        Session sessions = getSession();
        sessions.delete(book);
        sessions.flush();
        return book;
    }

    @Override
    public Book getById(long id) {
        Session session1 = session.openSession();
        Book book = session1.load(Book.class, id, LockMode.PESSIMISTIC_READ);
        return book;
    }
}
