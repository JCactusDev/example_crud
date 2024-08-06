package com.github.jcactusdev.example_crud.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.jcactusdev.example_crud.entity.ClientOrder;

public interface ClientOrderRepository extends CrudRepository<ClientOrder, Long> {

}