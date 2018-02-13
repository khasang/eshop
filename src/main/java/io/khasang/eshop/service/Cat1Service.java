package io.khasang.eshop.service;

import io.khasang.eshop.entity.Cat1;

import java.util.List;

public interface Cat1Service {
    /**
     * method for receiving all cats from DB
     *
     * @return List of all cats
     **/

    // void getAllCats
    List<Cat1> getAllCats();

    /**
     * method for receiving specify cat by id
     *
     * @param id = cat's id
     * @return cat by id
     **/
    Cat1 getById(long id);

    /**
     * method for add cat
     *
     * @param cat1 = new cat
     * @return created cat
     **/
    Cat1 addCat(Cat1 cat1);

    /**
     * method for delete specify cat by id
     *
     * @param id = cat's id
     * @return cat by id
     **/
    Cat1 deleteCat(long id);

    /**
     * method for update specify cat by id
     *
     * @param cat1 = new cat
     * @return updated cat
     **/
    Cat1 updateCat(Cat1 cat1);

    Cat1 patchCat(Cat1 cat1);
}
