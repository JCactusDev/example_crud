package com.github.jcactusdev.example_crud.service;

import com.github.jcactusdev.example_crud.entity.Organization;

public interface OrganizationService {

    Organization create(Organization organization);

    Iterable<Organization> read();

    Organization read(Long id);

    Organization updateObject(Organization organization);

    Organization updateObjectParams(Organization organization);

    void delete(Organization organization);

    Organization findById(Long id);

    boolean existsById(Long id);

    boolean existsByName(String Name);

}