package io.khasang.eshop;

import io.khasang.eshop.entity.Book;
import org.junit.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class BookControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/book";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";
    private static final String DELETE  = "/delete";
    private static final String UPDATE  = "/update";

    @Before
    public void init(){
        System.out.println("init");
    }

    @BeforeClass
    public static void globalinit(){
        System.out.println("Global init");
    }

    @Test
    public void addBook(){

        Book book = createdBook();

        RestTemplate template = new RestTemplate();

        ResponseEntity<Book> responseEntity = template.exchange(
                ROOT  + GET + "/{id}",
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
    public void updateBook(){
        Book book  = updatedBook();

        RestTemplate template = new RestTemplate();

        ResponseEntity<Book> responseEntity = template.exchange(
                ROOT  + GET + "/{id}",
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

    private Book updatedBook() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Book book = prefillBook1("Book1");
        HttpEntity<Book> httpEntity = new HttpEntity<>(book, headers);
        RestTemplate template = new RestTemplate();

        Book updatedBook = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                Book.class

        ).getBody();
        assertNotNull(updatedBook);
        assertEquals(book.getName(), updatedBook.getName());

        return updatedBook;

    }

    private Book createdBook() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Book book = prefillBook("textbook");

        HttpEntity<Book> httpEntity = new HttpEntity<>(book, headers);
        RestTemplate template = new RestTemplate();

        Book createdBook  = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Book.class
        ).getBody();
        assertNotNull(createdBook);
        assertEquals(createdBook.getName(), createdBook.getName());
        return createdBook;

    }

    private Book prefillBook(String book_name) {
        Book book = new Book();
        book.setName("book_name");
        book.setDescription("good");
        return  book;
    }

    private Book prefillBook1(String book_name) {
        Book book = new Book();
        book.setId(4);
        book.setName("book_name1");
        book.setDescription("good");
        book.setAdditional("");
        return  book;
    }

    @Test
    public void getAllBook(){
        createdBook();
        createdBook();

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Book>>  responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>() {
                }
        );
        List<Book> catList = responseEntity.getBody();
        assertNotNull(catList.get(0));
        assertNotNull(catList.get(1));

    }

    @Test
    public void deleteBook(){
        Book cat = createdBook();
        RestTemplate template = new RestTemplate();
        ResponseEntity<Book> responseEntity = template.exchange(
                ROOT + DELETE + "/?id={id}",
                HttpMethod.DELETE,
                null,
                Book.class,
                cat.getId()
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
        assertEquals("OK", responseForDeleteBook.getStatusCode().getReasonPhrase());
        assertNull(responseForDeleteBook.getBody());
    }

    @After
    public void clean(){
        System.out.println("Clean");
    }

    @AfterClass
    public static void globalClean(){
        System.out.println("Global Clean");
    }

}
