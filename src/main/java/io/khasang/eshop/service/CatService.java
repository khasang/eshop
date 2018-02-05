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
     * method for receiving distinct cat by id
     *
     * @param  id = cat's id
     * @return cat by id
     */
    Cat getById(long id);

    /**
     * method for adding cat
     *
     * @param  cat = new cat
     * @return created cat
     */
    Cat addCat(Cat cat);

    /**
     * method for deleting distinct cat by id
     *
     * @param  id = cat's id
     * @return cat by id
     */
    Cat delete(long id);

    /**
     * method for updating cat
     *
     * @param  cat = updated cat
     * @return updated cat
     */
    Cat updateCat(Cat cat);

    /**
     * method for patching cat
     *
     * @param  cat = patched cat
     * @return patched cat
     */
    Cat patchCat(Cat cat);
}
