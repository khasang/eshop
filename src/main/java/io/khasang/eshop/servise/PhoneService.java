package io.khasang.eshop.servise;

import io.khasang.eshop.entity.Phone;

import java.util.List;

public interface PhoneService {

    /**
     * method for receiving all Phone from DB
     *
     * @return List of all Phone
     */
    List<Phone> allPhone();

    /**
     * method for receiving specify Phone by id
     *
     * @param id = Phone's id
     * @return Phone by id
     */
    Phone getById(long id);

    /**
     * method for add phone
     *
     * @param phone = new phone
     * @return created phone
     */
    Phone addPhone(Phone phone);

    /**
     * method for delete specify phone by id
     *
     * @param id = phone's id
     * @return phone by id
     */
    Phone deletePhone(long id);

    /**
     * method for update specify phone
     *
     * @param phone = cat's id
     * @return update phone
     */
    Phone updatePhone(Phone phone);
}
