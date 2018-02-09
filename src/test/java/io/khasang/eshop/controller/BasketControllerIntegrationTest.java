package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Basket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class BasketControllerIntegrationTest {
    private final static String ROOT = "http://localhost:8080/basket";
    private final static String ADD = "/add";
    private final static String UPDATE = "/update";
    private final static String DELETE = "/delete";
    private final static String GETLIST = "/get";

    @Before
    public void init() {
        System.out.println("Init");
    }

    @Test
    public void add() {
        Basket basket = createBasket("babi");
        assertEquals(2, basket.getQuantity());
        Basket basket1 = createBasket("babi");
        assertEquals(4, basket1.getQuantity());
        assertEquals(basket.getUser(), basket1.getUser());
        deleteProduct(basket1);
    }


    private Basket createBasket(String users) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Basket basket = newBasket(users);

        HttpEntity<Basket> httpEntity = new HttpEntity<>(basket, headers);
        RestTemplate template = new RestTemplate();

        List<Basket> basketList = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<List<Basket>>() {
                }
        ).getBody();
        Basket createBasket = basketList.get(basketList.size() - 1);

        assertNotNull(createBasket);
        assertEquals(basket.getUser(), createBasket.getUser());
        assertNotNull(createBasket.getId());
        return createBasket;
    }

    private Basket newBasket(String users) {
        Basket basket = new Basket();
        basket.setUser(users);
        basket.setQuantity(2);
        basket.setGoods("Chicken");
        basket.setPrice(100);
        return basket;
    }

    private void deleteProduct(Basket basket) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Basket> httpEntity = new HttpEntity<>(basket, headers);

        RestTemplate restTemplate = new RestTemplate();
        List<Basket> basketList = restTemplate.exchange(
                ROOT + DELETE,
                HttpMethod.DELETE,
                httpEntity,
                new ParameterizedTypeReference<List<Basket>>() {
                }
        ).getBody();
        assertEquals(0, basketList.size());
    }

    @After
    public void clean() {
        System.out.println("Clean");
    }
}
