package io.khasang.eshop.dto;

import io.khasang.eshop.entity.Car;
import io.khasang.eshop.entity.Employee;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarDTO {
    private long id;
    private LocalDate year;
    private String model;
    private List<EmployeeDTO> employeeList = new ArrayList<>();

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<CarDTO> getCarDTOList(List<Car> carList) {
        List<CarDTO> carDTOS = new ArrayList<>();

        for (Car car : carList) {
            List<EmployeeDTO> employeeDTOS = new ArrayList<>();

            CarDTO carDTO = new CarDTO();
            carDTO.setId(car.getId());
            carDTO.setModel(car.getModel());
            carDTO.setYear(car.getYear());

            for (Employee employee : car.getEmployeeList()) {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setId(employee.getId());
                employeeDTO.setFunctions(employee.getFunctions());
                employeeDTO.setName(employee.getName());

                employeeDTOS.add(employeeDTO);
            }

            carDTO.setEmployeeList(employeeDTOS);
            carDTOS.add(carDTO);
        }

        return carDTOS;
    }

    public void setEmployeeList(List<EmployeeDTO> employeeList) {
        this.employeeList = employeeList;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
