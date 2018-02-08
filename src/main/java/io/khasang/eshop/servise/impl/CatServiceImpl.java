package io.khasang.eshop.servise.impl;

import io.khasang.eshop.dao.CatDao;
import io.khasang.eshop.entity.Cat;
import io.khasang.eshop.servise.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("catService")
public class CatServiceImpl implements CatService {
    @Autowired
    private CatDao catDao;

    @Override
    public List<Cat> getAllCats() {
        return catDao.getList();
    }

    @Override
    public Cat getById(long id) {
        return catDao.getById(id);
    }

    @Override
    public Cat addCat(Cat cat) {
        return catDao.add(cat);
    }

    @Override
    public Cat delete(long id) {
        return catDao.delete(getById(id));
    }

    @Override
    public Cat updateCat(Cat cat) {
        return catDao.update(cat);
    }
}
