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

import com.github.jcactusdev.example_crud.entity.Product;
import com.github.jcactusdev.example_crud.service.ProductServiceImpl;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    @Autowired
    private ProductServiceImpl service;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        if (product == null
                || product.getId() != null
                || product.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return (service.existsByName(product.getName()) ? new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY) : new ResponseEntity<>(service.create(product), HttpStatus.CREATED));
    }

    @GetMapping
    public ResponseEntity<List<Product>> read() {
        List<Product> result = service.read();
        return (result.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(result, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> readById(@PathVariable("id") Long id) {
        if (id == null
                || id < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return (!service.existsById(id) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(service.read(id), HttpStatus.OK));
    }

    @PutMapping
    public ResponseEntity<Product> updateObject(@RequestBody Product product) {
        if (product == null
                || product.getId() == null
                || product.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!service.existsById(product.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product result = service.updateObject(product);
        return result == null ? new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED) : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Product> updateObjectParams(@RequestBody Product product) {
        if (product == null
                || product.getId() == null
                || product.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!service.existsById(product.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product result = service.updateObjectParams(product);
        return result == null ? new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED) : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteById(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!service.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}