package com.github.jcactusdev.example_crud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jcactusdev.example_crud.entity.Client;
import com.github.jcactusdev.example_crud.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Override
    public Client create(Client client) {
        return repository.save(client);
    }

    @Override
    public List<Client> read() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Client read(Long id) {
        return findById(id);
    }

    @Override
    public Client updateObject(Client client) {
        Client currentClient = findById(client.getId());
        client.setId(currentClient.getId());
        return repository.save(client);
    }

    @Override
    public Client updateObjectParams(Client client) {
        Client currentClient = findById(client.getId());
        if (client.getName() != null) {
            currentClient.setName(client.getName());
        }
        return repository.save(currentClient);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Client findById(Long id) {
        Optional<Client> result = repository.findById(id);
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
