package com.github.jcactusdev.example_crud.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.jcactusdev.example_crud.entity.ClientOrderPosition;

public interface ClientOrderPositionRepository extends CrudRepository<ClientOrderPosition, Long> {

}