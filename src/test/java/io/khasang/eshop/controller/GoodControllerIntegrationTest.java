package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Good;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class GoodControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/good";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String DELETE = "/delete";
    private static final String ALL = "/all";
    private static final String UPDATE = "/update";

    @Test
    public void addGood(){
        Good good = createdGood();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Good> responseEntity = restTemplate.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Good.class,
                good.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Good received = responseEntity.getBody();
        assertNotNull(received);
        assertEquals(good.getId(), received.getId());
        assertNotNull(received.getDescription());
    }

    @Test
    public void getAllGood(){
        createdGood();
        createdGood();

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Good>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Good>>() {
                }
        );

        List<Good> goodList = responseEntity.getBody();
        assertNotNull(goodList.get(0));
        assertNotNull(goodList.get(1));
    }

    @Test
    public void updateGood(){
        Good good = createdGood();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Good updatedGood = update(good);
        HttpEntity<Good> httpEntity = new HttpEntity<>(updatedGood, httpHeaders);
        RestTemplate template = new RestTemplate();
        Good receivedGood = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                Good.class
        ).getBody();

        assertNotNull(receivedGood);
        assertEquals(updatedGood.getName(), receivedGood.getName());
        assertEquals(updatedGood.getDescription(), receivedGood.getDescription());
        assertEquals(updatedGood.getId(), receivedGood.getId());
    }

    private Good update(Good good) {
        good.setName("Updated good");
        good.setDescription("Updated description");
        return good;
    }

    @Test
    public void deleteGood(){
        Good good = createdGood();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Good> responseEntity = template.exchange(
                ROOT + DELETE + "/?id={id}",
                HttpMethod.DELETE,
                null,
                Good.class,
                good.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Good deletedGood = responseEntity.getBody();
        assertNotNull(deletedGood.getName());

        ResponseEntity<Good> responseForDeletedGood = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Good.class,
                deletedGood.getId()
        );

        assertEquals("OK", responseForDeletedGood.getStatusCode().getReasonPhrase());
        assertNull(responseForDeletedGood.getBody());
    }

    private Good createdGood() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Good good = prefillGood("Barsik");

        HttpEntity<Good> httpEntity = new HttpEntity<>(good, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();

        Good createdGood = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Good.class
        ).getBody();

        assertNotNull(createdGood);
        assertEquals(good.getName(), createdGood.getName());

        return createdGood;
    }

    private Good prefillGood(String barsik) {
        Good good = new Good();
        good.setName(barsik);
        good.setDescription("happy");
        return good;
    }
}
