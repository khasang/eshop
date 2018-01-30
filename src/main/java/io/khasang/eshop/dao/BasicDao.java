package io.khasang.eshop.dao;

import org.hibernate.Session;

import java.util.List;

public interface BasicDao<T> {
    /**
     * method for receiving all entities from DB
     *
     * @return List of all entity
     */
    List<T> getList();

    /**
     * get current Hibernate session
     * @return current Hibernate session
     */
    T getById(long id);

    Session getSession();
}
