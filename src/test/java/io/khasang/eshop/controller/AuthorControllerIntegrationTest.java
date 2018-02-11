package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Author;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AuthorControllerIntegrationTest {
    private final String ROOT = "http://localhost:8085/author";
    private final String ADD = "/add";
    private final String GET = "/get";
    private final String UPDATE = "/update";
    private final String DELETE = "/delete";

    @Test
    public void addAuthor() {
        Author author = createAuthor();

        Author receivedAuthor = getAuthor(author.getId());
        assertNotNull(receivedAuthor);
        assertEquals(author.getId(), receivedAuthor.getId());
    }

    @Test
    public void updateAuthor() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Author author = createAuthor();
        author.setName("new name");

        HttpEntity<Author> httpEntity = new HttpEntity<>(author, headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<Author> responseEntity = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                Author.class
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Author receivedAuthor = getAuthor(author.getId());
        assertNotNull(receivedAuthor);
        assertEquals(author.getId(), receivedAuthor.getId());
        assertEquals(author.getName(), receivedAuthor.getName());
    }

    @Test
    public void deleteAuthor() {
        Author author = createAuthor();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Author> deletedEntiry = restTemplate.exchange(
                ROOT + DELETE + "?id={id}",
                HttpMethod.DELETE,
                null,
                Author.class,
                author.getId()
        );

        assertEquals("OK", deletedEntiry.getStatusCode().getReasonPhrase());

        Author receivedAuthor = getAuthor(author.getId());
        assertNull(receivedAuthor);
    }

    private Author createAuthor() {
        Author author = fillAuthor();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Author> httpEntity = new HttpEntity<>(author, headers);
        RestTemplate template = new RestTemplate();
        Author createdAuthor = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Author.class
        ).getBody();

        assertNotNull(createdAuthor);
        assertEquals(author.getName(), createdAuthor.getName());
        return createdAuthor;
    }

    private Author fillAuthor() {
        Author author = new Author();
        author.setName("Author");
        return author;
    }

    private Author getAuthor(Long id) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<Author> responseEntity = template.exchange(
                ROOT + GET + "?id={id}",
                HttpMethod.GET,
                null,
                Author.class,
                id
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        return responseEntity.getBody();
    }
}
