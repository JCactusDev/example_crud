package com.github.jcactusdev.example_crud.service;

import com.github.jcactusdev.example_crud.entity.Client;

public interface ClientService {

    Client create(Client client);

    Iterable<Client> read();

    Client read(Long id);

    Client updateObject(Client client);

    Client updateObjectParams(Client client);

    void delete(Client client);

    void deleteAll();

    Client findById(Long id);

    boolean existsById(Long id);

    boolean existsByName(String Name);

    long count();

}