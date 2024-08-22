package com.github.jcactusdev.example_crud.controller;

import com.github.jcactusdev.example_crud.entity.Client;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientRestControllerTest {

    @Autowired
    protected ClientRestController controller;

    protected static Client client;

    static {
        client = new Client();
        client.setName("Alphabet Inc.");
    }

    @Test
    @Order(1)
    void create() {
        ResponseEntity<Client> response = controller.create(client);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(client.getName(), response.getBody().getName());
    }

    @Test
    @Order(2)
    public void createDuplicate() throws Exception {
        Client clientClone = client.clone();
        ResponseEntity<Client> response = controller.create(clientClone);
        assertEquals(response.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
        assertNull(response.getBody());
    }

    @Test
    @Order(3)
    void read() {
        ResponseEntity<List<Client>> response = controller.read();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertThat(response.getBody(), hasSize(1));
        assertThat(response.getBody(), hasItems(client));
    }

    @Test
    @Order(4)
    void readById() {
        ResponseEntity<Client> response = controller.readById(client.getId());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), client);
    }

    @Test
    @Order(5)
    void updateObject() {
        String newName = "Amazon";
        client.setName(newName);

        ResponseEntity<Client> response = controller.updateObject(client);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertThat(response.getBody(), hasProperty("name", equalTo(newName)));
        assertEquals(response.getBody(), client);
    }

    @Test
    @Order(6)
    void updateObjectParams() {
        String newName = "Amazon";
        client.setName(newName);

        ResponseEntity<Client> response = controller.updateObject(client);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertThat(response.getBody(), hasProperty("name", equalTo(newName)));
        assertEquals(response.getBody(), client);
    }

//    @Test
//    @Order(7)
//    void deleteById() {
//        ResponseEntity<Client> response = controller.deleteById(client.getId());
//        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
//        assertNull(response.getBody());
//    }
}