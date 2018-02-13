package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Author;
import io.khasang.eshop.entity.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class BookControllerIntegrationTest {

    private final static String ROOT = "http://localhost:8080/book";
    private final static String ADD = "/add";

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

    private void createBook() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Book book = newBook();
        HttpEntity<Book> httpEntity = new HttpEntity<>(book, headers);

        RestTemplate template = new RestTemplate();
        template.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Book.class
        );
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
