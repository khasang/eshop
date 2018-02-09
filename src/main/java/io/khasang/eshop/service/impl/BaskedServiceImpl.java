package io.khasang.eshop.service.impl;

import io.khasang.eshop.dao.BasketDao;
import io.khasang.eshop.entity.Basket;
import io.khasang.eshop.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "basketService")
public class BaskedServiceImpl implements BasketService {

     @Autowired
     public BasketDao basketDao;

    @Override
    public List<Basket> getGoodsByUser(String user){
        return basketDao.getGoodsByUser(user);
    }

    @Override
    public Basket addBasket(Basket product) {
        return basketDao.add(product);
    }

    @Override
    public List<Basket> delete(Basket product) {
        return basketDao.deleteByProduct(product);
    }

    @Override
    public Basket updateBasket(Basket product) {
        return basketDao.update(product);
    }
}
