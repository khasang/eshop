package io.khasang.eshop.service;

import io.khasang.eshop.entity.Car;
import io.khasang.eshop.entity.Cat;

import java.util.List;

public interface CarService {
    /**
     * method for receiving all cars from DB
     *
     * @return List of all cars
     */
    List<Car> getAllCars();

    /**
     * method for receiving specify car by id
     *
     * @param id = car's id
     * @return car by id
     */
    Car getById(long id);

    /**
     * method for add cat
     *
     * @param car = new cat
     * @return created cat
     */
    Car addCar(Car car);
}
