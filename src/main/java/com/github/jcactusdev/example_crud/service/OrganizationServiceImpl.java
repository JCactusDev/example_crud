package com.github.jcactusdev.example_crud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jcactusdev.example_crud.entity.Organization;
import com.github.jcactusdev.example_crud.repository.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository repository;

    @Override
    public Organization create(Organization organization) {
        return repository.save(organization);
    }

    @Override
    public List<Organization> read() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Organization read(Long id) {
        return findById(id);
    }

    @Override
    public Organization updateObject(Organization organization) {
        Organization currentOrganization = findById(organization.getId());
        organization.setId(currentOrganization.getId());
        return repository.save(organization);
    }

    @Override
    public Organization updateObjectParams(Organization organization) {
        Organization currentOrganization = findById(organization.getId());
        if (organization.getName() != null) {
            currentOrganization.setName(organization.getName());
        }
        if (organization.getTaxNumber() != null) {
            currentOrganization.setTaxNumber(organization.getTaxNumber());
        }
        return repository.save(currentOrganization);
    }

    @Override
    public void delete(Organization organization) {
        repository.delete(organization);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Organization findById(Long id) {
        Optional<Organization> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
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