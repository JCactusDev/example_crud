package com.github.jcactusdev.example_crud.service;

import com.github.jcactusdev.example_crud.entity.Product;

public interface ProductService {

    Product create(Product product);

    Iterable<Product> read();

    Product read(Long id);

    Product updateObject(Product product);

    Product updateObjectParams(Product product);

    void delete(Product product);

    void deleteAll();

    Product findById(Long id);

    boolean existsById(Long id);

    boolean existsAllByIdIn(Iterable<Long> ids);

    boolean existsByName(String Name);

    long count();

}