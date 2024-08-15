package com.github.jcactusdev.example_crud.controller;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jcactusdev.example_crud.entity.Organization;
import com.github.jcactusdev.example_crud.service.OrganizationServiceImpl;

@RestController
@RequestMapping("/api/organization")
public class OrganizationRestController {

    @Autowired
    private OrganizationServiceImpl service;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Organization> create(@RequestBody Organization organization) {
        if (organization == null
                || organization.getId() != null
                || organization.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return (service.existsByName(organization.getName()) ? new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY) : new ResponseEntity<>(service.create(organization), HttpStatus.CREATED));
    }

    @GetMapping
    public ResponseEntity<List<Organization>> read() {
        List<Organization> result = service.read();
        return (result.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(result, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organization> readById(@PathVariable("id") Long id) {
        if (id == null
                || id < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return (!service.existsById(id) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(service.read(id), HttpStatus.OK));
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Organization> updateObject(@RequestBody Organization organization) {
        if (organization == null
                || organization.getId() == null
                || organization.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!service.existsById(organization.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Organization result = service.updateObject(organization);
        return result == null ? new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED) : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Organization> updateObjectParams(@RequestBody Organization organization) {
        if (organization == null
                || organization.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!service.existsById(organization.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Organization result = service.updateObjectParams(organization);
        return result == null ? new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED) : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Organization> deleteObject(@RequestBody Organization organization) {
        if (organization == null
                || organization.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!service.existsById(organization.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.delete(organization);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}