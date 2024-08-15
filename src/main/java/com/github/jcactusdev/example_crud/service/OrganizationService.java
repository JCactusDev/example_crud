package com.github.jcactusdev.example_crud.service;

import com.github.jcactusdev.example_crud.entity.Organization;

import java.util.List;

public interface OrganizationService {

    Organization create(Organization organization);

    List<Organization> read();

    Organization read(Long id);

    Organization updateObject(Organization organization);

    Organization updateObjectParams(Organization organization);

    void delete(Organization organization);

    void deleteAll();

    Organization findById(Long id);

    boolean existsById(Long id);

    boolean existsByName(String Name);

    long count();

}