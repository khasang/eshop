package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Employee;
import org.junit.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;
public class EmployeeControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/employee";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";
    private static final String DELETE  = "/delete";
    private static final String UPDATE  = "/update";

    @Before
    public void init() {
        System.out.println("init");
    }

    @BeforeClass
    public static void globalinit(){
        System.out.println("Global init");
    }

    @Test
    public void addEmployee(){
        Employee employee = createdEmployee();

        RestTemplate template = new RestTemplate();

        ResponseEntity<Employee> responseEntity = template.exchange(
                ROOT  + GET + "/{id}",
                HttpMethod.GET,
                null,
                Employee.class,
                employee.getId()
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Employee receivedEmployee = responseEntity.getBody();
        assertNotNull(receivedEmployee);
        assertEquals(employee.getId(), receivedEmployee.getId());
        assertNotNull(receivedEmployee.getFunctions());
    }

    @Test
    public void updateEmployee(){
        Employee employee = updatedEmployee();

        RestTemplate template = new RestTemplate();

        ResponseEntity<Employee> responseEntity = template.exchange(
                ROOT  + GET + "/{id}",
                HttpMethod.GET,
                null,
                Employee.class,
                employee.getId()
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Employee receivedBook = responseEntity.getBody();
        assertNotNull(receivedBook);
        assertEquals(employee.getId(), receivedBook.getId());
        assertNotNull(receivedBook.getFunctions());

    }

    private Employee createdEmployee() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Employee employee = prefillEmployee("Employee");

        HttpEntity<Employee> httpEntity = new HttpEntity<>(employee, headers);
        RestTemplate template = new RestTemplate();

        Employee createdEmployee = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Employee.class
        ).getBody();
        assertNotNull(createdEmployee);
        assertEquals(createdEmployee.getName(), createdEmployee.getName());
        return createdEmployee;
    }

    private Employee updatedEmployee() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Employee employee = prefillEmployee1("Employee1");
        HttpEntity<Employee> httpEntity = new HttpEntity<>(employee, headers);
        RestTemplate template = new RestTemplate();

        Employee updatedEmployee = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                Employee.class

        ).getBody();

        assertNotNull(updatedEmployee);
        assertEquals(employee.getName(), updatedEmployee.getName());

        return updatedEmployee;

    }

    private Employee prefillEmployee(String employee_name) {
        Employee employee = new Employee();
        employee.setName(employee_name);
        employee.setFunctions("manager");
        return  employee;
    }

    private Employee prefillEmployee1(String employee_name1) {
        Employee employee = new Employee();
        employee.setId(4);
        employee.setName(employee_name1);
        employee.setFunctions("manager1");
        return  employee;
    }

    @Test
    public void getAllEmployee(){
        createdEmployee();
        createdEmployee();

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Employee>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {
                }
        );
        List<Employee> employeeList = responseEntity.getBody();
        assertNotNull(employeeList.get(0));
        assertNotNull(employeeList.get(1));
    }

    @Test
    public void deleteEmployee(){
        Employee cat = createdEmployee();
        RestTemplate template = new RestTemplate();
        ResponseEntity<Employee> responseEntity = template.exchange(
                ROOT + DELETE + "/?id={id}",
                HttpMethod.DELETE,
                null,
                Employee.class,
                cat.getId()
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Employee deletedEmployee = responseEntity.getBody();
        assertNotNull(deletedEmployee.getName());

        ResponseEntity<Employee> responseForDeleteEmployee = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Employee.class,
                deletedEmployee.getId()
        );
        assertEquals("OK", responseForDeleteEmployee.getStatusCode().getReasonPhrase());
        assertNull(responseForDeleteEmployee.getBody());
    }

    @After
    public void clean(){
        System.out.println("Clean");
    }

    @AfterClass
    public static void globalClean(){
        System.out.println("Global Clean");
    }

}
