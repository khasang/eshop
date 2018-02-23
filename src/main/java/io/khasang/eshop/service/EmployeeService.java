package io.khasang.eshop.service;

import io.khasang.eshop.entity.Employee;

import java.util.List;

public interface EmployeeService {
    /**
     * method for receiving all employes from DB
     *
     * @return List of all employes
     */
    List<Employee> getAllEmployees();

    /**
     * method for receiving specify cat by id
     *
     * @param id = employees id
     * @return employee by id
     */
    Employee getById(long id);

    /**
     * method for add employee
     *
     * @param employee = new employee
     * @return created employee
     */
    Employee addEmployee(Employee employee);

    /**
     * method for delete specify employee by id
     *
     * @param id = employees id
     * @return employee by id
     */
    Employee delete(long id);

    /**
     * method for update employee
     *
     * @param employee = updated employee
     * @return updated employee
     */
    Employee updateEmployee(Employee employee);

    /**
     * method for patch employee
     *
     * @param employee = patched employee
     * @return patched employee
     */
    Employee patchEmployee(Employee employee);
}
