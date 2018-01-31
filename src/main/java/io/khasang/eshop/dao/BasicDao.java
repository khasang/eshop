package io.khasang.eshop.dao;

import io.khasang.eshop.entity.Cat;
import org.hibernate.Session;

import java.util.List;

public interface BasicDao<T> {
    /**
     * method for receiving all entities from DB
     *
     * @return List of all entity
     **/
    List<T> getList();

    /**
     *
     * get current hibernate session
     *
     * @return current hibernate session
     */
   Session getSession();

    T getById(long id);

    /**
     * method for add cat
     *
     * @param entity = new entity
     * @return created entity
     **/
    T add(T entity);

    /**
     * method for delete specify entity
     *
     * @param entity = entity for delete
     * @return entity
     **/

    T delete(T entity);

    /**
     * method for update specify entity
     *
     * @param entity = entity for update
     * @return entity
     **/
    T update(T entity);
}
