package io.khasang.eshop.dao;

import io.khasang.eshop.entity.Book;
import org.hibernate.Session;

import java.util.List;

public interface BasicDao<T> {
    List<T> getList();

    Session getSessionFactory();

    T getById(long id);
}
