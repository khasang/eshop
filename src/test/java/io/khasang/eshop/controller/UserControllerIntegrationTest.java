package io.khasang.eshop.controller;
import io.khasang.eshop.entity.User;
import org.junit.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UserControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/user";
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
public void addUser() {
        User user = createdUser();

        RestTemplate template = new RestTemplate();

        ResponseEntity<User> responseEntity = template.exchange(
        ROOT + GET + "/{id}",
        HttpMethod.GET,
        null,
                User.class,
        user.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
    User receivedUser = responseEntity.getBody();
        assertNotNull(receivedUser);
        assertEquals(user.getId(), receivedUser.getId());
        assertNotNull(receivedUser.getDescription());
        }

@Test
public void getAllUser() {
        createdUser();
        createdUser();

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<User>> responseEntity = template.exchange(
        ROOT + ALL,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<User>>() {
        }
        );

        List<User> userList = responseEntity.getBody();
        assertNotNull(userList.get(0));
        assertNotNull(userList.get(1));
        }

@Test
public void deleteUser() {
    User user = createdUser();

        RestTemplate template = new RestTemplate();
        ResponseEntity<User> responseEntity = template.exchange(
        ROOT + DELETE + "/?id={id}",
        HttpMethod.DELETE,
        null,
                User.class,
        user.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

    User deletedUser = responseEntity.getBody();
        assertNotNull(deletedUser.getName());

        ResponseEntity<User> responseForDeleteUser = template.exchange(
        ROOT + GET + "/{id}",
        HttpMethod.GET,
        null,
                User.class,
        deletedUser.getId()
        );

        assertEquals("OK", responseForDeleteUser.getStatusCode().getReasonPhrase());
        assertNull(responseForDeleteUser.getBody());
        }

private User createdUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

    User user = prefillUser("userName");

        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        RestTemplate template = new RestTemplate();

    User createdUser = template.exchange(
        ROOT + ADD,
        HttpMethod.POST,
        httpEntity,
            User.class
        ).getBody();

                assertNotNull(createdUser);
                assertEquals(user.getName(), createdUser.getName());
                return createdUser;
                }

private User prefillUser(String userName) {
        User user = new User();
        user.setName(userName);
        user.setDescription("Ivan");
        return user;
        }
}
