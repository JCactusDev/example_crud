package com.github.jcactusdev.example_crud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jcactusdev.example_crud.entity.ClientOrder;
import com.github.jcactusdev.example_crud.repository.ClientOrderRepository;

@Service
public class ClientOrderServiceImpl implements ClientOrderService {

    @Autowired
    private ClientOrderRepository repository;

    @Override
    public ClientOrder create(ClientOrder clientOrder) {
        return repository.save(clientOrder);
    }

    @Override
    public Iterable<ClientOrder> read() {
        return repository.findAll();
    }

    @Override
    public ClientOrder read(Long id) {
        return findById(id);
    }

    @Override
    public ClientOrder updateObject(ClientOrder clientOrder) {
        ClientOrder currentClientOrder = findById(clientOrder.getId());
        clientOrder.setId(currentClientOrder.getId());
        return repository.save(clientOrder);
    }

    @Override
    public ClientOrder updateObjectParams(ClientOrder clientOrder) {
        ClientOrder currentClientOrder = findById(clientOrder.getId());
        if (clientOrder.getOrganization() != null) {
            currentClientOrder.setOrganization(clientOrder.getOrganization());
        }
        if (clientOrder.getClient() != null) {
            currentClientOrder.setClient(clientOrder.getClient());
        }
        if (clientOrder.getStatus() != null) {
            currentClientOrder.setStatus(clientOrder.getStatus());
        }
        if (clientOrder.getPositions() != null) {
            currentClientOrder.setPositions(clientOrder.getPositions());
        }
        return repository.save(currentClientOrder);
    }

    @Override
    public void delete(ClientOrder clientOrder) {
        repository.delete(clientOrder);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public ClientOrder findById(Long id) {
        Optional<ClientOrder> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

}