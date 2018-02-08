package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Phone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class PhoneControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/phone";
    private final String ADD = "/add";
    private final String ALL = "/all";
    private final String GET = "/get";
    private final String UPDATE = "/update";
    private final String DELETE = "/delete";

    @Before
    public void init() {
        System.out.println("Init");
    }

    @After
    public void clean() {
        System.out.println("Clean");
    }

    @Test
    public void getPhone() {
        Phone phone = createPhone();

        RestTemplate template = new RestTemplate();

        ResponseEntity<Phone> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Phone.class,
                phone.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody().getModel());
    }

    @Test
    public void getALL(){
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Phone>> response = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Phone>>() {
                }
        );

        List<Phone> list = response.getBody();
        assertNotNull(list.get(0));
        assertNotNull(list.get(1));
    }

    @Test
    public void updatePhone(){
        Phone phone = createPhone();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        phone.setColor("Pink");
        HttpEntity<Phone> httpEntity = new HttpEntity<>(phone, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Phone> responseEntity = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                httpEntity,
                Phone.class
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertEquals("Pink", responseEntity.getBody().getColor());
    }

    @Test(expected = NullPointerException.class)
    public void deletePhone(){
        Phone phone = createPhone();
        RestTemplate template = new RestTemplate();
        ResponseEntity<Phone> responseEntity = template.exchange(
                ROOT + DELETE + "/?id={id}",
                HttpMethod.DELETE,
                null,
                Phone.class,
                phone.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());

        assertNull(template.exchange(
                ROOT + GET +"/{id}",
                HttpMethod.GET,
                null,
                Phone.class,
                phone.getId()
        ).getBody());

        template.exchange(
                ROOT + GET +"/{id}",
                HttpMethod.GET,
                null,
                Phone.class,
                phone.getId()
        ).getBody().getColor();
    }

    public Phone createPhone() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Phone phone = prefillPhone();

        HttpEntity<Phone> httpEntity = new HttpEntity<>(phone, headers);

        RestTemplate template = new RestTemplate();

        Phone createPhone = template.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Phone.class
        ).getBody();

        assertNotNull(createPhone);
        assertEquals(phone.getModel(), createPhone.getModel());
        assertNotNull(createPhone.getId());
        return createPhone;
    }

    private Phone prefillPhone() {
        Phone phone = new Phone();
        phone.setColor("Black");
        phone.setModel("P10");
        phone.setManufacturer("Huawei");
        return phone;
    }
}
