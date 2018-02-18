package io.khasang.eshop.service.impl;

import io.khasang.eshop.dao.UserDao;
import io.khasang.eshop.entity.User;
import io.khasang.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getList();
    }

    @Override
    public User addUser(User user) {
        return userDao.add(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public User patchUser(User user) {
        return userDao.update(user);
    }

    @Override
    public User delete(long id) {
        return userDao.delete(getById(id));
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }
}
