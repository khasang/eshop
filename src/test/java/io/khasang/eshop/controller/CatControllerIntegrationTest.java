package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Cat;
import io.khasang.eshop.entity.CatWoman;
import org.junit.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CatControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/cat";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";
    private static final String DELETE = "/delete";

    @BeforeClass
    public static void globalInit() {
        System.out.println("Global init");
    }

    @AfterClass
    public static void globalClean() {
        System.out.println("Global Clean");
    }

    @Before
    public void init() {
        System.out.println("Init");
    }

    @After
    public void clean() {
        System.out.println("Clean");
    }

    @Test
    public void addCat() {
        Cat cat = createdCat();

        RestTemplate template = new RestTemplate();

        ResponseEntity<Cat> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                cat.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Cat receivedCat = responseEntity.getBody();
        assertNotNull(receivedCat);
        assertEquals(cat.getId(), receivedCat.getId());
        assertNotNull(receivedCat.getDescription());
    }

    @Test
    public void getAllCat() {
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
    public void deleteCat() {
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

        ResponseEntity<Cat> responseForDeleteCat = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                deletedCat.getId()
        );

        assertEquals("OK", responseForDeleteCat.getStatusCode().getReasonPhrase());
        assertNull(responseForDeleteCat.getBody());
    }

    private Cat createdCat() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat cat = prefillCat("Barsik");

        HttpEntity<Cat> httpEntity = new HttpEntity<>(cat, headers);
        RestTemplate template = new RestTemplate();

        Cat createdCat = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Cat.class
        ).getBody();

        assertNotNull(createdCat);
        assertEquals(cat.getName(), createdCat.getName());
        assertNotNull(cat.getCatWomanList());
        return createdCat;
    }

    private Cat prefillCat(String name ) {
        Cat cat = new Cat();
        cat.setName(name);
        cat.setDescription("happy");

        CatWoman catWoman1 = new CatWoman();

        catWoman1.setName("Riska");
        CatWoman catWoman2 = new CatWoman();
        catWoman2.setName("Murka");

        List<CatWoman> catWomanList = new ArrayList<>();
        catWomanList.add(catWoman1);
        catWomanList.add(catWoman2);

        cat.setCatWomanList(catWomanList);
        return cat;
    }
}
