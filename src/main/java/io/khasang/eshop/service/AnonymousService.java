package io.khasang.eshop.service;

import io.khasang.eshop.entity.Anonymous;

import java.util.List;

public interface AnonymousService {

    /**
     * method for receiving all anonymous from DB
     * @return List of all anonymous
     */
    List<Anonymous> getAllAnonymous();

    /**
     * method for receiving specify anonymous by id
     * @param id = anonymous id
     * @return anonymous by id
     */
    Anonymous addAnonymous(Anonymous anonymous);

    /**
     * method for update anonymous
     * @param anonymous = updated anonymous
     * @return updated anonymous
     */
    Anonymous updateAnonymous(Anonymous anonymous);

    /**
     * method for update anonymous
     * @param anonymous = updated anonymous
     * @return updated anonymous
     */
    Anonymous patchAnonymous(Anonymous anonymous);

    /**
     * method for delete specify anonymous by id
     *
     * @param id = anonymous id
     * @return anonymous by id
     */
    Anonymous delete(long id);

    /**
     * method for getbyid for anonymous
     * @param id = anonymous
     * @return anonymous
     */
    Anonymous getById(long id);
}
