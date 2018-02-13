package io.khasang.eshop;

import io.khasang.eshop.entity.Cat1;
import io.khasang.eshop.entity.CatWoman1;
import org.junit.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class Cat1ControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/cat1";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";
    private static final String DELETE  = "/delete";
    private static final String UPDATE  = "/update";

    @Before
    public void init(){
        System.out.println("init");
    }

    @BeforeClass
    public static void globalinit(){
        System.out.println("Global init");
    }

    @After
    public void clean(){
        System.out.println("Clean");
    }

    @AfterClass
    public static void globalClean(){
        System.out.println("Global Clean");
    }

    @Test
    public void addCat(){
        Cat1 cat1 = createdCat1();

        RestTemplate template = new RestTemplate();

        ResponseEntity<Cat1> responseEntity = template.exchange(
                ROOT  + GET + "/{id}",
                HttpMethod.GET,
                null,
                Cat1.class,
                cat1.getId()
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Cat1 receivedCat1 = responseEntity.getBody();
        assertNotNull(receivedCat1);
        assertEquals(cat1.getId(), receivedCat1.getId());
        assertNotNull(receivedCat1.getName());
    }

    @Test
    public void updateCat(){

        Cat1 cat1 = updatedCat1();

        RestTemplate template = new RestTemplate();

        ResponseEntity<Cat1> responseEntity = template.exchange(
                ROOT  + GET + "/{id}",
                HttpMethod.GET,
                null,
                Cat1.class,
                cat1.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Cat1 receivedCat1 = responseEntity.getBody();
        assertNotNull(receivedCat1);
        assertEquals(cat1.getId(), receivedCat1.getId());
        assertNotNull(receivedCat1.getName());
    }

    @Test
    public void getAllCat(){
        createdCat1();
        createdCat1();

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Cat1>>  responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cat1>>(){
                }
        );
        List<Cat1> catList = responseEntity.getBody();
        assertNotNull(catList.get(0));
        assertNotNull(catList.get(1));
    }

    @Test
    public void deleteCat(){
        Cat1 cat1 = createdCat1();
        RestTemplate template = new RestTemplate();
        ResponseEntity<Cat1> responseEntity = template.exchange(
                ROOT + DELETE + "/?id={id}",
                HttpMethod.DELETE,
                null,
                Cat1.class,
                cat1.getId()
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Cat1 deletedCat1 = responseEntity.getBody();
        assertNotNull(deletedCat1.getName());

        ResponseEntity<Cat1> responseForDeleteCat1 = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Cat1.class,
                deletedCat1.getId()
        );
        assertEquals("OK", responseForDeleteCat1.getStatusCode().getReasonPhrase());
        assertNull(responseForDeleteCat1.getBody());
    }

    private Cat1 updatedCat1(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat1 cat1 = prefillCat1("Pushok");
        HttpEntity<Cat1> httpEntity = new HttpEntity<>(cat1, headers);
        RestTemplate template = new RestTemplate();

        Cat1 updatedCat1 = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                Cat1.class

        ).getBody();
        assertNotNull(updatedCat1);
        assertEquals(cat1.getName(), updatedCat1.getName());

        return updatedCat1;
    }

    private Cat1 createdCat1() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat1 cat1 = prefillCat("name");

        HttpEntity<Cat1> httpEntity = new HttpEntity<>(cat1, headers);
        RestTemplate template = new RestTemplate();
        Cat1 createdCat1 = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Cat1.class
        ).getBody();
        assertNotNull(createdCat1);
        assertEquals(cat1.getName(), createdCat1.getName());
        assertNotNull(cat1.getCatWoman1());
        return createdCat1;
    }

    private Cat1 prefillCat(String name) {
        Cat1 cat1 = new Cat1();
        cat1.setName(name);
        cat1.setName("Pushok1");

        CatWoman1 catWoman1 = new CatWoman1();
        catWoman1.setName("Kukla1");
        catWoman1.setColor("pink");

        cat1.setCatWoman1(catWoman1);

        return cat1;
    }

    private Cat1 prefillCat1(String cat_name) {
        Cat1 cat1 = new Cat1();
        cat1.setId((long) 1);
        cat1.setName(cat_name);
        return cat1;
    }


}
