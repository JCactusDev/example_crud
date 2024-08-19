package com.github.jcactusdev.example_crud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.github.jcactusdev.example_crud.entity.ClientOrderPosition;
import com.github.jcactusdev.example_crud.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jcactusdev.example_crud.entity.ClientOrder;
import com.github.jcactusdev.example_crud.repository.ClientOrderRepository;

@Service
public class ClientOrderServiceImpl implements ClientOrderService {

    @Autowired
    private ClientOrderRepository repository;

    @Autowired
    private OrganizationServiceImpl organizationServiceImpl;

    @Autowired
    private ClientServiceImpl clientServiceImpl;

    @Override
    public ClientOrder create(ClientOrder clientOrder) {
        return repository.save(clientOrder);
    }

    @Override
    public List<ClientOrder> read() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
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
    public void deleteById(Long id) {
        repository.deleteById(id);
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