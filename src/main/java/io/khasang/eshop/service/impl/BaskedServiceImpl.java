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
    public List<Basket> getAllBasket() {
        return basketDao.getList();
    }

    @Override
    public List<Basket> getGoodsByUser(String user){
        return basketDao.getGoodsByUser(user);
    }

    @Override
    public Basket getById(long id) {
        return basketDao.getById(id);
    }

    @Override
    public Basket addBasket(Basket basket) {
        return basketDao.add(basket);
    }

    @Override
    public List<Basket> delete(Basket basket) {
        return basketDao.deleteByEntity(basket);
    }

    @Override
    public Basket updateBasket(Basket basket) {
        return basketDao.update(basket);
    }
}
