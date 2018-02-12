package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Author;
import io.khasang.eshop.entity.Book;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import static org.junit.Assert.*;

public class BookControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/book";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String DELETE = "/delete";
    private static final String ALL = "/all";
    private static final String UPDATE = "/update";

    @Test
    public void addBook(){
        Book book = createdBook();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Book> responseEntity = restTemplate.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Book.class,
                book.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Book received = responseEntity.getBody();
        assertNotNull(received);
        assertEquals(book.getId(), received.getId());
        assertNotNull(received.getDescription());
    }

    @Test
    public void getAllBook(){
        createdBook();
        createdBook();

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Book>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>() {
                }
        );

        List<Book> bookList = responseEntity.getBody();
        assertNotNull(bookList.get(0));
        assertNotNull(bookList.get(1));
    }

    @Test
    public void updateBook(){
        Book book = createdBook();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Book updatedBook = update(book);
        HttpEntity<Book> httpEntity = new HttpEntity<>(updatedBook, httpHeaders);
        RestTemplate template = new RestTemplate();
        Book receivedBook = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                Book.class
        ).getBody();

        assertNotNull(receivedBook);
        assertEquals(updatedBook.getName(), receivedBook.getName());
        assertEquals(updatedBook.getDescription(), receivedBook.getDescription());
        assertEquals(updatedBook.getId(), receivedBook.getId());
    }

    @Test
    public void deleteBook(){
        Book book = createdBook();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Book> responseEntity = template.exchange(
                ROOT + DELETE + "/?id={id}",
                HttpMethod.DELETE,
                null,
                Book.class,
                book.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Book deletedBook = responseEntity.getBody();
        assertNotNull(deletedBook.getName());

        ResponseEntity<Book> responseForDeletedBook = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Book.class,
                deletedBook.getId()
        );

        assertEquals("OK", responseForDeletedBook.getStatusCode().getReasonPhrase());
        assertNull(responseForDeletedBook.getBody());
    }

    private Book update(Book book) {
        book.setName("Updated book");
        book.setDescription("Updated description");
        return book;
    }

    private Book createdBook() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Book book = createBook();
        HttpEntity<Book> bookHttpEntity = new HttpEntity<>(book, httpHeaders);
        RestTemplate template = new RestTemplate();
        Book createdBook = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                bookHttpEntity,
                Book.class
        ).getBody();

        assertNotNull(createdBook);
        assertEquals(book.getDescription(), createdBook.getDescription());

        return createdBook;
    }

    private Book createBook() {
        Book book = new Book();

        Author author1 = new Author();
        Author author2 = new Author();

        author1.setFirstName("Ilya");
        author1.setLastName("Ilf");
        author1.getBookList().add(book);

        author2.setFirstName("Evgeny");
        author2.setLastName("Petrov");
        author2.getBookList().add(book);

        book.setName("Golden Calf");
        book.setDescription("Novel");
        book.getAuthorList().add(author1);
        book.getAuthorList().add(author2);

        return book;
    }
}
