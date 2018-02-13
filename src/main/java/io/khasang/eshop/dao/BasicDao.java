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
     *
     * @return current Hibernate session
     */
    T getById(long id);

    Session getSession();

    /**
     * method for add entity
     *
     * @param entity = new entity
     * @return created entity
     */
    T add(T entity);
    void add();
    /**
     * method for delete specify entity
     *
     * @param entity = entity for delete
     * @return entity
     */
    T delete(T entity);

    /**
     * method for update specify entity
     *
     * @param entity = updated entity
     * @return updated entity
     */
    T update(T entity);
}
