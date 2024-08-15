package com.github.jcactusdev.example_crud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jcactusdev.example_crud.entity.Product;
import com.github.jcactusdev.example_crud.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> read() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Product read(Long id) {
        return findById(id);
    }

    @Override
    public Product updateObject(Product product) {
        Product currentProduct = findById(product.getId());
        product.setId(currentProduct.getId());
        return repository.save(product);
    }

    @Override
    public Product updateObjectParams(Product product) {
        Product currentProduct = findById(product.getId());
        if (product.getName() != null) {
            currentProduct.setName(product.getName());
        }
        return repository.save(currentProduct);
    }

    @Override
    public void delete(Product product) {
        repository.delete(product);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsAllByIdIn(Iterable<Long> ids) {
        Long countUniqIds = StreamSupport.stream(ids.spliterator(), false).distinct().count();
        return countUniqIds.equals(repository.countByIdIn(ids));
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public long count() {
        return repository.count();
    }

}