package io.khasang.eshop.dao;

import io.khasang.eshop.entity.Book;
import org.hibernate.Session;

import java.util.List;

public interface BasicDao<T> {
    List<T> getList();

    Session getSession();

    T getById(long id);

    T add(T entity);

    T delete(T entity);

    T update(T entity);
}
