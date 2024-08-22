package com.github.jcactusdev.example_crud.controller;

import com.github.jcactusdev.example_crud.entity.Organization;

import com.github.jcactusdev.example_crud.entity.OrganizationType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
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
        organization.setFullName("Organization FULL 1");
        organization.setShortName("Organization SHORT 1");
        organization.setType(OrganizationType.LegalPerson);
        organization.setTaxNumber("TAX NUMBER 1");
        organization.setRegDate(LocalDate.of(2000, 1, 2));
        organization.setRegNumber("REG NUMBER 1");
    }

    @Test
    @Order(1)
    protected void create() {
        ResponseEntity<Organization> response = controller.create(organization);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
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
        assertEquals(response.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
        assertNull(response.getBody());
    }

    @Test
    @Order(3)
    public void read() {
        ResponseEntity<List<Organization>> response = controller.read();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertThat(response.getBody(), hasSize(1));
        assertThat(response.getBody(), hasItems(organization));
    }

    @Test
    @Order(4)
    public void readById() {
        ResponseEntity<Organization> response = controller.readById(organization.getId());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), organization);
    }

    @Test
    @Order(5)
    public void updateObject() {
        String newTaxNumber = "TAX NUMBER 01";
        organization.setTaxNumber(newTaxNumber);

        ResponseEntity<Organization> response = controller.updateObject(organization);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
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

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertThat(response.getBody(), hasProperty("taxNumber", equalTo(newTaxNumber)));
        assertEquals(response.getBody(), organization);
    }

//    @Test
//    @Order(7)
//    public void deleteById() {
//        ResponseEntity<Organization> response = controller.deleteById(organization.getId());
//        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
//        assertNull(response.getBody());
//    }
}