package com.github.jcactusdev.example_crud.controller;

import com.github.jcactusdev.example_crud.entity.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.github.jcactusdev.example_crud.controller.ClientRestControllerTest.client;
import static com.github.jcactusdev.example_crud.controller.OrganizationRestControllerTest.organization;
import static com.github.jcactusdev.example_crud.controller.ProductRestControllerTest.product;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientOrderRestControllerTest {

    @Autowired
    protected ClientOrderRestController controller;

    protected static ClientOrder clientOrder;

    static {
        clientOrder = new ClientOrder();
        clientOrder.setId(null);
        clientOrder.setOrganization(organization);
        clientOrder.setClient(client);
        clientOrder.setStatus(ClientOrderStatus.NOTAGREED);

        ClientOrderPosition position0 = new ClientOrderPosition();
        position0.setProduct(product);
        position0.setCount(10);
        position0.setPrice(100);

        clientOrder.addPosition(position0);
    }

    @Test
    @Order(1)
    void create() {
        ResponseEntity<ClientOrder> response = controller.create(clientOrder);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(clientOrder.getOrganization(), response.getBody().getOrganization());
        assertEquals(clientOrder.getClient(), response.getBody().getClient());
        assertEquals(clientOrder.getStatus(), response.getBody().getStatus());
        assertThat(response.getBody().getPositions(), hasSize(1));
    }

    @Test
    @Order(2)
    void read() {
        ResponseEntity<List<ClientOrder>> response = controller.read();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertThat(response.getBody(), hasSize(1));
    }

    @Test
    @Order(3)
    void readById() {
        ResponseEntity<ClientOrder> response = controller.readById(clientOrder.getId());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(clientOrder.getOrganization(), response.getBody().getOrganization());
        assertEquals(clientOrder.getClient(), response.getBody().getClient());
        assertEquals(clientOrder.getStatus(), response.getBody().getStatus());
    }

    @Test
    @Order(4)
    void updateObject() {
        ClientOrderPosition position1 = new ClientOrderPosition();
        position1.setProduct(product);
        position1.setCount(20);
        position1.setPrice(200);
        clientOrder.addPosition(position1);

        ResponseEntity<ClientOrder> response = controller.updateObjectParams(clientOrder);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(clientOrder.getOrganization(), response.getBody().getOrganization());
        assertEquals(clientOrder.getClient(), response.getBody().getClient());
        assertEquals(clientOrder.getStatus(), response.getBody().getStatus());
        assertThat(response.getBody().getPositions(), hasSize(2));
    }

    @Test
    @Order(5)
    void updateObjectParams() {
        ClientOrderPosition position1 = new ClientOrderPosition();
        position1.setProduct(product);
        position1.setCount(30);
        position1.setPrice(300);
        clientOrder.addPosition(position1);

        ResponseEntity<ClientOrder> response = controller.updateObjectParams(clientOrder);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(clientOrder.getOrganization(), response.getBody().getOrganization());
        assertEquals(clientOrder.getClient(), response.getBody().getClient());
        assertEquals(clientOrder.getStatus(), response.getBody().getStatus());
        assertThat(response.getBody().getPositions(), hasSize(3));
    }

    @Test
    @Order(6)
    void delete() {
        ResponseEntity<ClientOrder> response = controller.deleteById(clientOrder.getId());
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
        assertNull(response.getBody());
    }
}