package io.khasang.eshop.dao;

import org.hibernate.Session;

import java.util.List;

public interface BasicDao<T> {
    List<T> getList();

    Session getSessionFactory();
}
