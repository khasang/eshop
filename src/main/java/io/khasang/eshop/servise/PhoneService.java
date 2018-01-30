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
     * @param id = Phone's id
     * @return Phone by id
     */
    Phone getById(long id);
}
