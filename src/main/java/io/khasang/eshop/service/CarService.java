package io.khasang.eshop.service;

import io.khasang.eshop.dto.CarDTO;
import io.khasang.eshop.entity.Car;
import io.khasang.eshop.entity.Cat;

import java.util.List;

public interface CarService {
    /**
     * method for receiving all cars from DB
     *
     * @return List of all cars
     */
    List<CarDTO> getAllCars();

    /**
     * method for receiving specify car by id
     *
     * @param id = car's id
     * @return car by id
     */
    CarDTO getById(long id);

    /**
     * method for add cat
     *
     * @param car = new cat
     * @return created cat
     */
    Car addCar(Car car);
}
