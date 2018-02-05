package io.khasang.eshop;

import io.khasang.eshop.entity.Cat;
import org.junit.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class CatControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/cat";
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

    @Test
    public void addCat(){
        Cat cat = createdCat();

        RestTemplate template = new RestTemplate();

        ResponseEntity<Cat> responseEntity = template.exchange(
                ROOT  + GET + "/{id}",
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

        Cat cat = updatedCat();

        RestTemplate template = new RestTemplate();

        ResponseEntity<Cat> responseEntity = template.exchange(
                ROOT  + GET + "/{id}",
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

    private Cat updatedCat(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat cat = prefillCat1("Pushok");
        HttpEntity<Cat> httpEntity = new HttpEntity<>(cat, headers);
        RestTemplate template = new RestTemplate();

        Cat updatedCat = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                Cat.class

        ).getBody();
        assertNotNull(updatedCat);
        assertEquals(cat.getName(), updatedCat.getName());

        return updatedCat;
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
       return createdCat;
    }

    private Cat prefillCat(String barsik) {
        Cat cat = new Cat();
        cat.setName(barsik);
        cat.setDescription("happy");
        return cat;
    }

    private Cat prefillCat1(String cat_name) {
        Cat cat = new Cat();
        cat.setId(13);
        cat.setName(cat_name);
        cat.setDescription("downy cat");
        cat.setAdditional("");
        return cat;
    }

    @Test
    public void getAllCat(){
        createdCat();
        createdCat();

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Cat>>  responseEntity = template.exchange(
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

//    @Test
//    public void getCatByName(){
//
//        RestTemplate template = new RestTemplate();
//
//        ResponseEntity<Cat> responseEntity = template.exchange(
//                ROOT + GET + "/{name}",
//                HttpMethod.GET,
//                null,
//                Cat.class,
//                "Barsik"
//        );
//        assertNotNull(responseEntity.getBody());
//    }

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

    @After
    public void clean(){
        System.out.println("Clean");
    }

    @AfterClass
    public static void globalClean(){
        System.out.println("Global Clean");
    }
}
