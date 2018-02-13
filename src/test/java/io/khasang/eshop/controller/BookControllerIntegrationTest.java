package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Author;
import io.khasang.eshop.entity.Book;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

public class BookControllerIntegrationTest {

    private final static String ROOT = "http://localhost:8080/book";
    private final static String ADD = "/add";
    private final static String GET = "/get";


    @Before
    public void init(){
        System.out.println("Init");
    }

    @After
    public void clear(){
        System.out.println("Clear");
    }

    @Test
    public void add(){
        createBook();
    }

    private Book createBook() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Book book = newBook();
        HttpEntity<Book> httpEntity = new HttpEntity<>(book, headers);

        RestTemplate template = new RestTemplate();
        ResponseEntity<Book> returnBook= template.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Book.class
        );

        assertEquals("OK", returnBook.getStatusCode().getReasonPhrase());
        assertEquals("Пси[ология", returnBook.getBody().getName());
        return book;
    }

    @Test
    public void getBook(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Book> responseEntity = restTemplate.exchange(
                ROOT + GET +"/{id}",
                HttpMethod.GET,
                null,
                Book.class,
                1
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Book book =  responseEntity.getBody();
        assertNotNull(book);
    }

    private Book newBook() {
        Book book = new Book();
        book.setName("Психология");
        Author author1 = new Author();
        Author author2 = new Author();
        author1.setName("Зигмунд Фрейд");
        author2.setName("Эрик берн");
        book.addAuthor(author1);
        book.addAuthor(author2);
        return book;
    }
}
