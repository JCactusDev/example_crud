package com.github.jcactusdev.example_crud.service;

import com.github.jcactusdev.example_crud.entity.ClientOrder;

public interface ClientOrderService {

    ClientOrder create(ClientOrder clientOrder);

    Iterable<ClientOrder> read();

    ClientOrder read(Long id);

    ClientOrder updateObject(ClientOrder clientOrder);

    ClientOrder updateObjectParams(ClientOrder clientOrder);

    void delete(ClientOrder clientOrder);

    void deleteAll();

    ClientOrder findById(Long id);

    boolean existsById(Long id);

    long count();

}