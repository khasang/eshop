package io.khasang.eshop.service;

import io.khasang.eshop.entity.Cat;

import java.util.List;

public interface CatService {
    /**
     * method for receiving all cats from DB
     *
     * @return List of all cats
     */
    List<Cat> getAllCats();

    /**
     * method for receiving specify cat by id
     *
     * @param id = cat's id
     * @return cat by id
     */
    Cat getById(long id);

    /**
     * method for add cat
     *
     * @param cat = new cat
     * @return created cat
     */
    Cat addCat(Cat cat);

    /**
     * method for delete specify cat by id
     *
     * @param id = cat's id
     * @return cat by id
     */
    Cat delete(long id);

    /**
     * method for update specify cat
     *
     * @param cat = updated cat
     * @return updated cat
     */
    Cat updateCat(Cat cat);
}
