package io.khasang.eshop.controller;

import io.khasang.eshop.dto.CarDTO;
import io.khasang.eshop.entity.Car;
import io.khasang.eshop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Car addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Car getCarById(@PathVariable(value = "id") String id) {
        return carService.getById(Long.parseLong(id));
    }
}
