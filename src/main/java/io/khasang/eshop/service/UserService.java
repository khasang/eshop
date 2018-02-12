package io.khasang.eshop.service;

import io.khasang.eshop.entity.Cat;
import io.khasang.eshop.entity.User;

import java.util.List;

public interface UserService {

    /**
     * method for receiving all user from DB
     * @return List of all user
     */
    List<User> getAllUsers();

    /**
     * method for receiving specify user by id
     * @param id = user's id
     * @return user by id
     */
    User addUser(User user);

    /**
     * method for update user
     * @param user = updated user
     * @return updated user
     */
    User updateUser(User user);

    /**
     * method for update user
     * @param user = updated user
     * @return updated user
     */
    User patchUser(User user);

    /**
     * method for delete specify user by id
     *
     * @param id = user's id
     * @return user by id
     */
    User delete(long id);

    /**
     * method for getbyid for user
     * @param id = user
     * @return user
     */
    User getById(long id);
}
