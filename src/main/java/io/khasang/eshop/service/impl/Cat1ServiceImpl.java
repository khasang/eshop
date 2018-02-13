package io.khasang.eshop.service.impl;

import io.khasang.eshop.dao.Cat1Dao;
import io.khasang.eshop.entity.Cat1;
import io.khasang.eshop.service.Cat1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cat1Service")
public class Cat1ServiceImpl implements Cat1Service {
    @Autowired
    private Cat1Dao cat1Dao;

    @Override
    public List<Cat1> getAllCats() {
        return cat1Dao.getList();
    }

    @Override
    public Cat1 getById(long id) {
        return cat1Dao.getById(id);
    }

    @Override
    public Cat1 addCat(Cat1 cat1) {
        return cat1Dao.add(cat1);
    }

    @Override
    public Cat1 deleteCat(long id) {
        return cat1Dao.delete(getById(id));
    }

    @Override
    public Cat1 updateCat(Cat1 cat1) {
        return cat1Dao.update(cat1);
    }

    @Override
    public Cat1 patchCat(Cat1 cat1) {
        return cat1Dao.update(cat1);
    }
}
