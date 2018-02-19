package io.khasang.eshop.service.impl;

import io.khasang.eshop.dao.GoodDao;
import io.khasang.eshop.entity.Good;
import io.khasang.eshop.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("goodService")
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodDao goodDao;

    @Override
    public List<Good> getAllGoods() {
        return goodDao.getList();
    }

    @Override
    public Good getById(long id) {
        return goodDao.getById(id);
    }

    @Override
    public Good addGood(Good good) {
        return goodDao.add(good);
    }

    @Override
    public Good updateGood(Good good) {
        return goodDao.update(good);
    }

    @Override
    public Good delete(long id) {
        return goodDao.delete(getById(id));
    }
}
