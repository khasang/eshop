package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Basket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BasketControllerIntegrationTest {
    private final static String ROOT = "http://localhost:8080/basket";
    private final static String ADD = "/add";
    private final static String UPDATE = "/update";
    private final static String DELETE = "/delete";
    private final static String CLEAR = "/clear";

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

        assertEquals("Bobi", deleteProduct(basket).getUser());
        assertEquals("asdqwe", deleteProduct(basket1).getUser());
        ResponseEntity<List<Basket>> responseEntity = getProductUser(basket);
        assertEquals(0, responseEntity.getBody().size());
    }

    @Test
    public void getGoodsByUser() {
        Basket basket = createBasket("Bobi", "Chiken");
        Basket basket1 = createBasket("Keyli", "Eggs");
        Basket basket2 = createBasket("Bobi", "Марс");

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Basket>> responseEntity = getProductUser(basket);
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        List<Basket> basketList = responseEntity.getBody();
        assertEquals(basketList.size(), 2);
        assertNotNull(basketList.get(0));
        assertEquals(basketList.get(0).getGoods(), basket.getGoods());
        assertEquals(basketList.get(1).getUser(), basket2.getUser());

        assertEquals("Bobi", deleteProduct(basket).getUser());
        assertEquals("Keyli", deleteProduct(basket1).getUser());
        assertEquals("Bobi", deleteProduct(basket2).getUser());

        responseEntity = getProductUser(basket);
        assertEquals(0, responseEntity.getBody().size());
    }

    @Test
    public void updateProductInBasket() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Basket basket = createBasket("Bobi", "Chicken");
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

        basket.setVersion(basket.getVersion() + 1);
        assertEquals("Bobi", deleteProduct(basket).getUser());
        assertEquals("Keyli", deleteProduct(basket1).getUser());
        assertEquals("Koort", deleteProduct(basket2).getUser());
    }

    @Test
    public void clearGoods() {
        Basket basket = createBasket("Bobi", "Chicken");
        Basket basket1 = createBasket("Bobi", "Eggs");
        Basket basket2 = createBasket("Bobi", "Mars");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Basket> responseEntity = restTemplate.exchange(
                ROOT + CLEAR + "/{user}",
                HttpMethod.DELETE,
                null,
                Basket.class,
                basket.getUser()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertEquals(0, getProductUser(basket).getBody().size());
    }

    @Test
    public void deleteProductInBasket() {
        Basket basket = createBasket("Bobi", "Chicken");
        Basket basket1 = createBasket("Keyli", "Eggs");
        Basket basket2 = createBasket("Koort", "Mars");
        Basket basket3 = createBasket("Bobi", "Eggs");

        assertEquals("Keyli", deleteProduct(basket1).getUser());
        assertEquals("Koort", deleteProduct(basket2).getUser());
        assertEquals("Bobi", deleteProduct(basket).getUser());
        assertEquals("Bobi", deleteProduct(basket3).getUser());
    }

    private ResponseEntity<List<Basket>> getProductUser(Basket basket) {
        RestTemplate template = new RestTemplate();
        return template.exchange(
                ROOT + "/{user}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Basket>>() {
                },
                basket.getUser()
        );
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

    private Basket deleteProduct(Basket basket) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Basket> httpEntity = new HttpEntity<>(basket, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Basket> responseEntity = restTemplate.exchange(
                ROOT + DELETE,
                HttpMethod.DELETE,
                httpEntity,
                Basket.class
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        return responseEntity.getBody();
    }

}
