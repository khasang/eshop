package io.khasang.eshop.controller;



import io.khasang.eshop.entity.Car;
import io.khasang.eshop.entity.Cat;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CarControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/car";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";

    @Test
    public void addCar() {
    private static final String DELETE = "/delete";

    @Test
    public void addCat() {
        Car car = createdCar();

        RestTemplate template = new RestTemplate();

        ResponseEntity<Car> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Car.class,
                car.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Car receivedCar = responseEntity.getBody();
        assertNotNull(receivedCar);
        assertEquals(car.getId(), receivedCar.getId());
        Car receivedCat = responseEntity.getBody();
        assertNotNull(receivedCat);
        assertEquals(car.getId(), receivedCat.getId());
    }

    @Test
    public void getAllCar() {
        createdCar();
        createdCar();

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Car>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Car>>() {
                }
        );

        List<Car> catList = responseEntity.getBody();
        assertNotNull(catList.get(0));
        assertNotNull(catList.get(1));
        ResponseEntity<List<CarDTO>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CarDTO>>() {
                }
        );

        List<CarDTO> carList = responseEntity.getBody();
        assertNotNull(carList.get(0));
        assertNotNull(carList.get(1));
    }

    private Car createdCar() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Car car = prefillCat("Vaz");
        Car car = prefillCar("Vaz");

        HttpEntity<Car> httpEntity = new HttpEntity<>(car, headers);
        RestTemplate template = new RestTemplate();

        Car createdCar = template.exchange(
        Car createdCat = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Car.class
        ).getBody();

        assertNotNull(createdCar);
        return createdCar;
    }

    private Car prefillCat(String model) {
        Car car = new Car();
        car.setModel(model);
        car.setYear(LocalDate.of(2017,11,12));
        Cat cat1 = new Cat();
        cat1.setName("sdcsd");

        Cat cat2 = new Cat();
        cat2.setName("sdcssdfsdd");

        List<Cat> cats = new ArrayList<>();
        cats.add(cat1);
        cats.add(cat2);
        car.setCatList(cats);

        return car;
    }

        assertNotNull(createdCat);
        return createdCat;
    }

    private Car prefillCar(String model) {
        Car car = new Car();
        car.setModel(model);
        car.setYear(LocalDate.of(2017, 11, 12));

        Employee employee1 = new Employee();
        employee1.setName("asdasd");

        Employee employee2 = new Employee();
        employee2.setName("aasdasdasd");

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        car.setEmployeeList(employees);

        return car;
    }
}
