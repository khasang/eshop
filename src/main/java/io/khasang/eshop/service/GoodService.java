package io.khasang.eshop.service;

import io.khasang.eshop.entity.Good;
import java.util.List;

public interface GoodService {

    /**
     * method for receiving all goods from DB
     *
     * @return List of all goods
     */
    List<Good> getAllGoods();

    /**
     * method for receiving specify good by id
     *
     * @param id = good's id
     * @return good by id
     */
    Good getById(long id);

    /**
     * method for add good
     *
     * @param good = new good
     * @return created good
     */
    Good addGood(Good good);

    /**
     * method for delete specify good by id
     *
     * @param id = good's id
     * @return good by id
     */
    Good delete(long id);

    /**
     * method for update good
     *
     * @param good = updated good
     * @return updated good
     */
    Good updateGood(Good good);

}
