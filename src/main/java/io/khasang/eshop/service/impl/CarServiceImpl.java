package io.khasang.eshop.service.impl;

import io.khasang.eshop.dao.CarDao;
import io.khasang.eshop.entity.Car;
import io.khasang.eshop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carService")
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> getAllCars() {
        return carDao.getList();
    }

    @Override
    public Car getById(long id) {
        return carDao.getById(id);
    }

    @Override
    public Car addCar(Car car) {
        return carDao.add(car);
    }
}
