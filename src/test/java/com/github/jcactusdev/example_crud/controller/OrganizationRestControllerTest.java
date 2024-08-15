package com.github.jcactusdev.example_crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jcactusdev.example_crud.entity.Organization;
import com.github.jcactusdev.example_crud.service.OrganizationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrganizationRestControllerTest {

    @Autowired
    protected OrganizationRestController controller;

    @Autowired
    protected OrganizationServiceImpl service;

    protected static Organization organization1;
    protected static Organization organization2;
    protected static Organization organization3;

    static {
        organization1 = new Organization();
        organization1.setId(null);
        organization1.setName("Organization 1");
        organization1.setTaxNumber("TAX NUMBER 1");

        organization2 = new Organization();
        organization2.setId(null);
        organization2.setName("Organization 2");
        organization2.setTaxNumber("TAX NUMBER 2");

        organization3 = new Organization();
        organization3.setId(null);
        organization3.setName("Organization 3");
        organization3.setTaxNumber("TAX NUMBER 3");
    }

    @BeforeEach
    void setUp() {
        service.deleteAll();
        service.create(organization1);
        service.create(organization2);
        service.create(organization3);
    }

    @Test
    public void create() throws Exception {
        Organization organization = new Organization();
        organization.setId(null);
        organization.setName("Test Organization 1");
        organization.setTaxNumber("TAX1");

        ResponseEntity<Organization> response = controller.create(organization);

        assertEquals(response.getStatusCode().value(), 201);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(organization.getName(), response.getBody().getName());
        assertEquals(organization.getTaxNumber(), response.getBody().getTaxNumber());
    }

    @Test
    public void read() throws Exception {
        ResponseEntity<List<Organization>> response = controller.read();

        assertEquals(response.getStatusCode().value(), 200);
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), List.of(organization1, organization2, organization3));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}