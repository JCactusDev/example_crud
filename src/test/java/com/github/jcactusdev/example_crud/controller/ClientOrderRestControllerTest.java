package com.github.jcactusdev.example_crud.controller;

import com.github.jcactusdev.example_crud.entity.Organization;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientOrderRestControllerTest {

    @Test
    @Order(1)
    void create() {
        fail("Not yet implemented");
    }

    @Test
    @Order(2)
    public void createDuplicate() {
        fail("Not yet implemented");
    }

    @Test
    @Order(3)
    void read() {
        fail("Not yet implemented");
    }

    @Test
    @Order(4)
    void readById() {
        fail("Not yet implemented");
    }

    @Test
    @Order(5)
    void updateObject() {
        fail("Not yet implemented");
    }

    @Test
    @Order(6)
    void updateObjectParams() {
        fail("Not yet implemented");
    }

    @Test
    @Order(7)
    void delete() {
        fail("Not yet implemented");
    }
}