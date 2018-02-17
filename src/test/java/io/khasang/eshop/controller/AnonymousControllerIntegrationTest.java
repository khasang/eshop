package io.khasang.eshop.controller;

import io.khasang.eshop.entity.Anonymous;
import org.junit.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AnonymousControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/anonymous";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";
    private static final String DELETE = "/delete";

    @BeforeClass
    public static void globalInit() {
        System.out.println("Global init");
    }

    @AfterClass
    public static void globalClean() {
        System.out.println("Global Clean");
    }

    @Before
    public void init() {
        System.out.println("Init");
    }

    @After
    public void clean() {
        System.out.println("Clean");
    }

    @Test
    public void addAnonymous() {
        Anonymous anonymous = createdAnonymous();

        RestTemplate template = new RestTemplate();

        ResponseEntity<Anonymous> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Anonymous.class,
                anonymous.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        Anonymous receivedAnonymous = responseEntity.getBody();
        assertNotNull(receivedAnonymous);
        assertEquals(anonymous.getId(), receivedAnonymous.getId());
        assertNotNull(receivedAnonymous.getDescription());
    }

    @Test
    public void getAllAnonymous() {
        createdAnonymous();
        createdAnonymous();

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Anonymous>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Anonymous>>() {
                }
        );

        List<Anonymous> anonymousList = responseEntity.getBody();
        assertNotNull(anonymousList.get(0));
        assertNotNull(anonymousList.get(1));
    }

    @Test
    public void deleteAnonymous() {
        Anonymous anonymous = createdAnonymous();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Anonymous> responseEntity = template.exchange(
                ROOT + DELETE + "/?id={id}",
                HttpMethod.DELETE,
                null,
                Anonymous.class,
                anonymous.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Anonymous deletedAnonymous = responseEntity.getBody();
        assertNotNull(deletedAnonymous.getName());

        ResponseEntity<Anonymous> responseForDeleteAnonymous = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Anonymous.class,
                deletedAnonymous.getId()
        );

        assertEquals("OK", responseForDeleteAnonymous.getStatusCode().getReasonPhrase());
        assertNull(responseForDeleteAnonymous.getBody());
    }

    private Anonymous createdAnonymous() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Anonymous anonymous = prefillAnonymous("anonym");

        HttpEntity<Anonymous> httpEntity = new HttpEntity<>(anonymous, headers);
        RestTemplate template = new RestTemplate();

        Anonymous createdAnonymous = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Anonymous.class
        ).getBody();

        assertNotNull(createdAnonymous);
        assertEquals(anonymous.getName(), createdAnonymous.getName());
        return createdAnonymous;
    }

    private Anonymous prefillAnonymous(String anonym) {
        Anonymous anonymous = new Anonymous();
        anonymous.setName(anonym);
        return anonymous;
    }
}
