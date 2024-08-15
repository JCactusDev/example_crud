package com.github.jcactusdev.example_crud.controller;

import com.github.jcactusdev.example_crud.entity.Organization;

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

    protected static Organization organization1;

    static {
        organization1 = new Organization();
        organization1.setId(null);
        organization1.setName("Organization 1");
        organization1.setTaxNumber("TAX NUMBER 1");
    }

    @BeforeAll
    static void setUp() {
        // При начале тестирования
        System.out.println("Тестирование запущено");
    }

    @AfterAll
    static void tearDownAll() {
        // При окончании тестирования
        System.out.println("Тестирование завершено");
    }

    @BeforeEach
    void init() throws Exception {
        // При начале каждого теста
    }

    @AfterEach
    void tearDown() throws Exception {
        // При окончании каждого теста
        //service.deleteAll();
    }

    @Test
    @Order(1)
    protected void create() throws Exception {
        ResponseEntity<Organization> response = controller.create(organization1);

        assertEquals(response.getStatusCode().value(), HttpStatus.CREATED.value());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(organization1.getName(), response.getBody().getName());
        assertEquals(organization1.getTaxNumber(), response.getBody().getTaxNumber());
    }

    @Test
    @Order(2)
    public void createDuplicate() throws Exception {
        Organization organization = organization1.clone();
        ResponseEntity<Organization> response = controller.create(organization);
        assertEquals(response.getStatusCode().value(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        assertNull(response.getBody());
    }

    @Test
    @Order(3)
    public void read() throws Exception {
        ResponseEntity<List<Organization>> response = controller.read();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertNotNull(response.getBody());
        assertThat(response.getBody(), hasSize(1));
        assertThat(response.getBody(), hasItems(organization1));
    }

    @Test
    @Order(4)
    public void readById() throws Exception {
        ResponseEntity<Organization> response = controller.readById(organization1.getId());

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertNotNull(response.getBody());
        assertEquals(response.getBody(), organization1);
    }

    @Test
    @Order(5)
    public void updateObject() throws Exception {
        String newTaxNumber = "TAX NUMBER 01";
        organization1.setTaxNumber(newTaxNumber);

        ResponseEntity<Organization> response = controller.updateObject(organization1);

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertNotNull(response.getBody());
        assertThat(response.getBody(), hasProperty("taxNumber", equalTo(newTaxNumber)));
        assertEquals(response.getBody(), organization1);
    }

    @Test
    @Order(6)
    public void updateObjectParams() throws Exception {
        String newTaxNumber = "TAX NUMBER 01";
        organization1.setTaxNumber(newTaxNumber);

        ResponseEntity<Organization> response = controller.updateObjectParams(organization1);

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertNotNull(response.getBody());
        assertThat(response.getBody(), hasProperty("taxNumber", equalTo(newTaxNumber)));
        assertEquals(response.getBody(), organization1);
    }

    @Test
    @Order(7)
    public void deleteObject() throws Exception {
        ResponseEntity<Organization> response = controller.deleteObject(organization1);
        assertEquals(response.getStatusCode().value(), HttpStatus.NO_CONTENT.value());
    }

//    private static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}