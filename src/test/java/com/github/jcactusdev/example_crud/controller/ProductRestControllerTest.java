package com.github.jcactusdev.example_crud.controller;

import com.github.jcactusdev.example_crud.entity.Organization;
import com.github.jcactusdev.example_crud.entity.Product;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
public class ProductRestControllerTest {

    @Autowired
    protected ProductRestController controller;

    protected static Product product;

    static {
        product = new Product();
        product.setId(null);
        product.setName("Book 1");
    }

    @Test
    @Order(1)
    void create() {
        ResponseEntity<Product> response = controller.create(product);

        assertEquals(response.getStatusCode().value(), HttpStatus.CREATED.value());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(product.getName(), response.getBody().getName());
    }

    @Test
    @Order(2)
    public void createDuplicate() throws Exception {
        Product productClone = product.clone();
        ResponseEntity<Product> response = controller.create(productClone);
        assertEquals(response.getStatusCode().value(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        assertNull(response.getBody());
    }

    @Test
    @Order(3)
    void read() {
        ResponseEntity<List<Product>> response = controller.read();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertNotNull(response.getBody());
        assertThat(response.getBody(), hasSize(1));
        assertThat(response.getBody(), hasItems(product));
    }

    @Test
    @Order(4)
    void readById() {
        ResponseEntity<Product> response = controller.readById(product.getId());

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), product);
    }

    @Test
    @Order(5)
    void updateObject() {
        String newName = "Book 01";
        product.setName(newName);

        ResponseEntity<Product> response = controller.updateObject(product);

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertNotNull(response.getBody());
        assertThat(response.getBody(), hasProperty("name", equalTo(newName)));
        assertEquals(response.getBody(), product);
    }

    @Test
    @Order(6)
    void updateObjectParams() {
        String newName = "Book 01";
        product.setName(newName);

        ResponseEntity<Product> response = controller.updateObject(product);

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertNotNull(response.getBody());
        assertThat(response.getBody(), hasProperty("name", equalTo(newName)));
        assertEquals(response.getBody(), product);
    }

    @Test
    @Order(7)
    void deleteObject() {
        ResponseEntity<Product> response = controller.deleteObject(product);
        assertEquals(response.getStatusCode().value(), HttpStatus.NO_CONTENT.value());
        assertNull(response.getBody());
    }
}