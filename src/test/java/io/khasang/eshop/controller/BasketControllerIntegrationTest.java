package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Basket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class BasketControllerIntegrationTest {
    private final static String ROOT = "http://localhost:8080/basket";
    private final static String ADD = "/add";
    private final static String UPDATE = "/update";
    private final static String DELETE = "/delete";

    @Before
    public void init() {
        System.out.println("Init");
    }

    @After
    public void clean() {
        System.out.println("Clean");
    }

    @Test
    public void addProductInBasket() {
        Basket basket = createBasket("Bobi", "Chicken");
        assertEquals(2, basket.getQuantity());

        Basket basket1 = createBasket("Bobi", "Chicken");
        assertEquals(4, basket1.getQuantity());
        assertEquals(basket.getUser(), basket1.getUser());

        Basket basket2 = createBasket("asdqwe", "Mars");
        assertEquals(basket2.getUser(), "asdqwe");
        assertEquals(basket2.getGoods(), "Mars");

        assertEquals(deleteProduct(basket1).size(), 0);
        assertEquals(deleteProduct(basket2).size(),0);
    }

    @Test
    public void getGoodsByUser() {
        Basket basket = createBasket("Bobi", "Chiken");
        Basket basket1 = createBasket("Kayli", "Eggs");
        Basket basket2 = createBasket("Bobi", "Марс");

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Basket>> responseEntity = template.exchange(
                ROOT + "/{user}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Basket>>() {
                },
                basket.getUser()
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        List<Basket> basketList = responseEntity.getBody();
        assertEquals(basketList.size(), 2);
        assertNotNull(basketList.get(0));
        assertEquals(basketList.get(0).getGoods(), basket.getGoods());
        assertEquals(basketList.get(1).getUser(), basket2.getUser());

        assertEquals(deleteProduct(basket).size(),1);
        assertEquals(deleteProduct(basket1).size(),0);
        assertEquals(deleteProduct(basket2).size(),0);
    }

    @Test
    public void updateProductInBasket() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Basket basket = createBasket("Bobi", "Chiken");
        Basket basket1 = createBasket("Keyli", "Eggs");
        Basket basket2 = createBasket("Koort", "Mars");

        basket.setQuantity(6);
        HttpEntity<Basket> httpEntity = new HttpEntity<>(basket, headers);
        ResponseEntity<List<Basket>> responseEntity = new RestTemplate().exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                new ParameterizedTypeReference<List<Basket>>() {
                }
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        List<Basket> basketList = responseEntity.getBody();
        assertNotNull(basketList);
        assertEquals(basketList.get(0).getQuantity(), basket.getQuantity());

        deleteProduct(basket);
        deleteProduct(basket1);
        assertEquals(deleteProduct(basket2).size(), 0);
    }

    @Test
    public void deleteProductInBasket() {
        Basket basket = createBasket("Bobi", "Chiken");
        Basket basket1 = createBasket("Keyli", "Eggs");
        Basket basket2 = createBasket("Koort", "Mars");
        Basket basket3 = createBasket("Bobi", "Eggs");

        assertEquals(deleteProduct(basket1).size(),0);
        assertEquals(deleteProduct(basket2).size(),0);
        assertEquals(deleteProduct(basket).size(), 1);
        assertEquals(deleteProduct(basket3).size(), 0);
    }

    private Basket createBasket(String users, String product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Basket basket = newBasket(users, product);

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

    private Basket newBasket(String users, String product) {
        Basket basket = new Basket();
        basket.setUser(users);
        basket.setQuantity(2);
        basket.setGoods(product);
        basket.setPrice(100);
        return basket;
    }

    private List<Basket> deleteProduct(Basket basket) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Basket> httpEntity = new HttpEntity<>(basket, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Basket>> responseEntity = restTemplate.exchange(
                ROOT + DELETE,
                HttpMethod.DELETE,
                httpEntity,
                new ParameterizedTypeReference<List<Basket>>() {
                }
        );
        return responseEntity.getBody();
    }

}
