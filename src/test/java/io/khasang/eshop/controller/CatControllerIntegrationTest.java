package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Cat;
import io.khasang.eshop.entity.CatWoman;
import org.junit.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CatControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/cat";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String DELETE = "/delete";
    private static final String ALL = "/all";
    private static final String UPDATE = "/update";

    @Before
    public void init(){
        System.out.println("Init");
    }

    @BeforeClass
    public static void globalInit(){
        System.out.println("Global Init");
    }

    @Test
    public void addCat(){
        Cat cat = createdCat();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cat> responseEntity = restTemplate.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                cat.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Cat received = responseEntity.getBody();
        assertNotNull(received);
        assertEquals(cat.getId(), received.getId());
        assertNotNull(received.getDescription());
    }

    @Test
    public void getAllCat(){
        createdCat();
        createdCat();

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Cat>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cat>>() {
                }
        );

        List<Cat> catList = responseEntity.getBody();
        assertNotNull(catList.get(0));
        assertNotNull(catList.get(1));
    }

    @Test
    public void updateCat(){
        Cat cat = createdCat();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat updatedCat = update(cat);
        HttpEntity<Cat> httpEntity = new HttpEntity<>(updatedCat, httpHeaders);
        RestTemplate template = new RestTemplate();
        Cat receivedCat = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                Cat.class
        ).getBody();

        assertNotNull(receivedCat);
        assertEquals(updatedCat.getName(), receivedCat.getName());
        assertEquals(updatedCat.getDescription(), receivedCat.getDescription());
        assertEquals(updatedCat.getId(), receivedCat.getId());
    }

    private Cat update(Cat cat) {
        cat.setName("Updated cat");
        cat.setDescription("Updated description");
        return cat;
    }

    @Test
    public void deleteCat(){
        Cat cat = createdCat();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Cat> responseEntity = template.exchange(
                ROOT + DELETE + "/?id={id}",
                HttpMethod.DELETE,
                null,
                Cat.class,
                cat.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Cat deletedCat = responseEntity.getBody();
        assertNotNull(deletedCat.getName());

        ResponseEntity<Cat> responseForDeletedCat = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                deletedCat.getId()
        );

        assertEquals("OK", responseForDeletedCat.getStatusCode().getReasonPhrase());
        assertNull(responseForDeletedCat.getBody());
    }

    private Cat createdCat() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat cat = prefillCat("Barsik");

        HttpEntity<Cat> httpEntity = new HttpEntity<>(cat, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();

        Cat createdCat = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Cat.class
        ).getBody();

        assertNotNull(createdCat);
        assertEquals(cat.getName(), createdCat.getName());
        assertNotNull(cat.getCatWoman());
        return createdCat;
    }

    private Cat prefillCat(String name) {
        Cat cat = new Cat();
        cat.setName(name);
        cat.setDescription("happy");

        CatWoman catWoman1 = new CatWoman();
        catWoman1.setName("Riska");
        cat.setCatWoman(catWoman1);

        return cat;
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
