package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Cat;
import org.junit.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CatControllerIntegrationTest {

    private final String ROOT = "http://localhost:8080/cats";
    private final String ADD = "/add";
    private final String GET = "/get";
    private final String ALL = "/all";
    private final String DELETE = "/delete";
    private final String UPDATE = "/update";

    //будет выполнять перед каждым методом
    @Before
    public void init(){
        System.out.println("Init");
    }

    //будет выполняться перед каждым классом
    @BeforeClass
    public static void globalInit(){
        System.out.println("Global init");
    }

    @Test
    public void addCat(){
        Cat cat = createdCat();

        RestTemplate template = new RestTemplate();

        ResponseEntity<Cat> responseEntity = template.exchange(
                ROOT + GET +"/{id}",
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
    public void updateCat(){
        Cat cat = createdCat();
        cat.setDescription("LyaLya");
        cat.setName("BARSIK");
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Cat> httpEntity = new HttpEntity<>(cat, headers);

        ResponseEntity<Cat> responseCat = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                Cat.class
        );

        Cat updateCat = responseCat.getBody();
        assertEquals("OK", responseCat.getStatusCode().getReasonPhrase());
        assertNotNull(updateCat.getName());
        assertEquals(cat.getName(),updateCat.getName());
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
                //используйется в параметризации типов запросов, что бы могли использовать какие то списки
                new ParameterizedTypeReference<List<Cat>>() {}
        );

        List<Cat> catList = responseEntity.getBody();
        assertNotNull(catList.get(0));
        assertNotNull(catList.get(1));
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

        Cat deleteCat = responseEntity.getBody();
        assertNotNull(deleteCat.getName());

        ResponseEntity<Cat> responseForDeleteCat = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                deleteCat.getId()
        );

        assertEquals("OK",responseForDeleteCat.getStatusCode().getReasonPhrase());
        assertNotNull(responseForDeleteCat);
    }

    private Cat createdCat() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat cat = prefillCat("Barsik");

        HttpEntity<Cat> httpEntity = new HttpEntity<>(cat, headers);
        //позволяет работать с веб клиентом Spring
        RestTemplate template = new RestTemplate();

        Cat createdCat = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Cat.class
        ).getBody();

        assertNotNull(createdCat);
        assertEquals(cat.getName(), createdCat.getName());
        return createdCat;
    }

    private Cat prefillCat(String barsik) {
        Cat cat = new Cat();
        cat.setName(barsik);
        cat.setDescription("happy");
        cat.setDateOfBirth(new Date());
        return cat;
    }

    //будет выполняться после каждого метода
    @After
    public void clean(){
        System.out.println("Clean");
    }

    //будет выполняться после каждого класса
    @AfterClass
    public static void globalClean(){
        System.out.println("Global Clean");
    }
}
