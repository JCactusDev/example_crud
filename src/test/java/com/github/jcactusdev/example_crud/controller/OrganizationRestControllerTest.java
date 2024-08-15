package com.github.jcactusdev.example_crud.controller;

import com.github.jcactusdev.example_crud.entity.Organization;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrganizationRestControllerTest {

    @Autowired
    protected OrganizationRestController controller;

    protected static Organization organization;

    static {
        organization = new Organization();
        organization.setId(null);
        organization.setName("Organization 1");
        organization.setTaxNumber("TAX NUMBER 1");
    }

    @Test
    @Order(1)
    protected void create() {
        ResponseEntity<Organization> response = controller.create(organization);

        assertEquals(response.getStatusCode().value(), HttpStatus.CREATED.value());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(organization.getName(), response.getBody().getName());
        assertEquals(organization.getTaxNumber(), response.getBody().getTaxNumber());
    }

    @Test
    @Order(2)
    public void createDuplicate() throws Exception {
        Organization organizationClone = organization.clone();
        ResponseEntity<Organization> response = controller.create(organizationClone);
        assertEquals(response.getStatusCode().value(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        assertNull(response.getBody());
    }

    @Test
    @Order(3)
    public void read() {
        ResponseEntity<List<Organization>> response = controller.read();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertNotNull(response.getBody());
        assertThat(response.getBody(), hasSize(1));
        assertThat(response.getBody(), hasItems(organization));
    }

    @Test
    @Order(4)
    public void readById() {
        ResponseEntity<Organization> response = controller.readById(organization.getId());

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), organization);
    }

    @Test
    @Order(5)
    public void updateObject() {
        String newTaxNumber = "TAX NUMBER 01";
        organization.setTaxNumber(newTaxNumber);

        ResponseEntity<Organization> response = controller.updateObject(organization);

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertNotNull(response.getBody());
        assertThat(response.getBody(), hasProperty("taxNumber", equalTo(newTaxNumber)));
        assertEquals(response.getBody(), organization);
    }

    @Test
    @Order(6)
    public void updateObjectParams() {
        String newTaxNumber = "TAX NUMBER 01";
        organization.setTaxNumber(newTaxNumber);

        ResponseEntity<Organization> response = controller.updateObjectParams(organization);

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertNotNull(response.getBody());
        assertThat(response.getBody(), hasProperty("taxNumber", equalTo(newTaxNumber)));
        assertEquals(response.getBody(), organization);
    }

    @Test
    @Order(7)
    public void deleteObject() {
        ResponseEntity<Organization> response = controller.deleteObject(organization);
        assertEquals(response.getStatusCode().value(), HttpStatus.NO_CONTENT.value());
        assertNull(response.getBody());
    }
}