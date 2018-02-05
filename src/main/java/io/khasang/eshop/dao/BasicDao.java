package io.khasang.eshop.dao;

import org.hibernate.Session;
import java.util.List;

public interface BasicDao<T> {
    /**
     * method for receiving all entities from DB
     *
     * @return List of all entities
     */
    List<T> getList();

    /**
     * get current hibernate session
     *
     * @return current hibernate session
     */
    Session getSession();

    /**
     * method for receiving distinct entity by id
     *
     * @param  id = entity's id
     * @return entity by id
     */

    T getById(long id);

    /**
     * method for adding entity
     *
     * @param  entity = new entity
     * @return created entity
     */
    T add(T entity);

    /**
     * method for deleting distinct entity
     *
     * @param  entity = entity for deletion
     * @return deleted entity
     */
    T delete(T entity);

    /**
     * method for updating entity
     *
     * @param  entity = updated entity
     * @return updated entity
     */
    T update(T entity);
}
