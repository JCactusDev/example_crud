package com.github.jcactusdev.example_crud.controller;

import java.util.List;
import java.util.stream.StreamSupport;

import com.github.jcactusdev.example_crud.entity.Organization;
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

import com.github.jcactusdev.example_crud.entity.Client;
import com.github.jcactusdev.example_crud.service.ClientServiceImpl;

@RestController
@RequestMapping("/api/client")
public class ClientRestController {

    @Autowired
    private ClientServiceImpl service;

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client) {
        if (client == null
                || client.getId() != null
                || client.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return (service.existsByName(client.getName()) ? new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY) : new ResponseEntity<>(service.create(client), HttpStatus.CREATED));
    }

    @GetMapping
    public ResponseEntity<List<Client>> read() {
        List<Client> result = service.read();
        return (result.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(result, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> readById(@PathVariable("id") Long id) {
        if (id == null
                || id < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return (!service.existsById(id) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(service.read(id), HttpStatus.OK));
    }

    @PutMapping
    public ResponseEntity<Client> updateObject(@RequestBody Client client) {
        if (client == null
                || client.getId() == null
                || client.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!service.existsById(client.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Client result = service.updateObject(client);
        return result == null ? new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED) : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Client> updateObjectParams(@RequestBody Client client) {
        if (client == null
                || client.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!service.existsById(client.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Client result = service.updateObjectParams(client);
        return result == null ? new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED) : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Client> deleteObject(@RequestBody Client client) {
        if (client == null
                || client.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!service.existsById(client.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.delete(client);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}