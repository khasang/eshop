package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Book;
import org.junit.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class BookControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8085/book";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String GETALL = "/all";
    private static final String UPDATE = "/update";
    private static final String DELETE = "/delete";

    @Before
    public void init() {
        System.out.println("Init");
    }

    @BeforeClass
    public static void globalInit() {
        System.out.println("Global init");
    }

    @Test()
    public void addBook() {
        Book book = createdBook();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Book> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Book.class,
                book.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Book receivedBook = responseEntity.getBody();
        assertNotNull(receivedBook);
        assertEquals(book.getId(), receivedBook.getId());
        assertNotNull(receivedBook.getDescription());
    }

    @Test
    public void getAllBooks() {
        createdBook();
        createdBook();
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Book>> responseEntity = template.exchange(
                ROOT + GETALL,
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
    public void update() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Book book = createdBook();
        book.setDescription("Good Book");

        HttpEntity<Book> httpEntity = new HttpEntity<>(book, headers);

        RestTemplate template = new RestTemplate();
        ResponseEntity<Book> responseEntity = template.exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                httpEntity,
                Book.class,
                book.getId()
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Book updatedBook = responseEntity.getBody();
        assertNotNull(updatedBook.getName());
        assertNotNull(updatedBook);
        ResponseEntity<Book> responseForUpdateBook = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Book.class,
                updatedBook.getId()
        );
        assertEquals("OK", responseForUpdateBook.getStatusCode().getReasonPhrase());
        assertEquals(book.getDescription(), updatedBook.getDescription());
    }

    @Test
    public void deleteBook() {
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
        ResponseEntity<Book> responseForDeleteBook = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Book.class,
                deletedBook.getId()
        );
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNull(responseForDeleteBook.getBody());
    }

    private Book createdBook() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Book book = prefillBook("War and Peace");
        HttpEntity<Book> httpEntity = new HttpEntity<>(book, headers);
        RestTemplate template = new RestTemplate();
        Book createdBook = template.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Book.class
        ).getBody();
        assertNotNull(createdBook);
        assertEquals(book.getName(), createdBook.getName());
        return createdBook;
    }

    private Book prefillBook(String war_and_peace) {
        Book book = new Book();
        book.setName(war_and_peace);
        book.setDescription("book");
        return book;
    }

    @After
    public void clean() {
        System.out.println("Clean");
    }

    @AfterClass
    public static void GlobalClean() {
        System.out.println("Global clean");
    }
}
