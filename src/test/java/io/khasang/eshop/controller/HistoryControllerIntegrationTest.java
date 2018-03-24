package io.khasang.eshop.controller;

import io.khasang.eshop.entity.History;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class HistoryControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/history";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String DELETE = "/delete";
    private static final String ALL = "/all";
    private static final String UPDATE = "/update";

    @Test
    public void addHistory() {
        History history = createdHistory();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<History> responseEntity = restTemplate.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                History.class,
                history.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        History received = responseEntity.getBody();
        assertNotNull(received);
        assertEquals(history.getId(), received.getId());
        assertNotNull(received.getOrders());
    }

    @Test
    public void getAllHistory() {
        createdHistory();
        createdHistory();

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<History>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<History>>() {
                }
        );

        List<History> historyList = responseEntity.getBody();
        assertNotNull(historyList.get(0));
        assertNotNull(historyList.get(1));
    }

    @Test
    public void updateHistory() {
        History history = createdHistory();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        History updatedHistory = update(history);
        HttpEntity<History> httpEntity = new HttpEntity<>(updatedHistory, httpHeaders);
        RestTemplate template = new RestTemplate();
        History receivedHistory = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                History.class
        ).getBody();

        assertNotNull(receivedHistory);
        assertEquals(updatedHistory.getUsers(), receivedHistory.getUsers());
        assertEquals(updatedHistory.getOrders(), receivedHistory.getOrders());
        assertEquals(updatedHistory.getId(), receivedHistory.getId());
    }

    @Test
    public void deleteHistory() {
        History history = createdHistory();

        RestTemplate template = new RestTemplate();
        ResponseEntity<History> responseEntity = template.exchange(
                ROOT + DELETE + "/{id}",
                HttpMethod.DELETE,
                null,
                History.class,
                history.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        History deletedHistory = responseEntity.getBody();
        assertNotNull(deletedHistory.getUsers());

        ResponseEntity<History> responseForDeletedHistory = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                History.class,
                deletedHistory.getId()
        );

        assertEquals("OK", responseForDeletedHistory.getStatusCode().getReasonPhrase());
        assertNull(responseForDeletedHistory.getBody());
    }

    private History createdHistory() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        History history = prefillHistory("Barsik");

        HttpEntity<History> httpEntity = new HttpEntity<>(history, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();

        History createdHistory = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                History.class
        ).getBody();

        assertNotNull(createdHistory);
        assertEquals(history.getUsers(), createdHistory.getUsers());

        return createdHistory;
    }

    private History prefillHistory(String name) {
        History history = new History();
        history.setUsers(name);
        history.setOrders("happy");
        history.setStatus("buy");
        history.setDate(new Date());
        return history;
    }

    private History update(History history) {
        history.setUsers("Updated good");
        history.setOrders("Updated orders");
        return history;
    }

}
