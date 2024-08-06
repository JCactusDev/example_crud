package com.github.jcactusdev.example_crud.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.jcactusdev.example_crud.entity.Client;
import com.github.jcactusdev.example_crud.entity.ClientOrder;
import com.github.jcactusdev.example_crud.entity.ClientOrderPosition;
import com.github.jcactusdev.example_crud.entity.Organization;
import com.github.jcactusdev.example_crud.service.ClientOrderServiceImpl;
import com.github.jcactusdev.example_crud.service.ClientServiceImpl;
import com.github.jcactusdev.example_crud.service.OrganizationServiceImpl;
import com.github.jcactusdev.example_crud.service.ProductServiceImpl;

@RestController
@RequestMapping("/api/clientorder")
public class ClientOrderRestController {

    @Autowired
    private ClientOrderServiceImpl clientOrderServiceImpl;

    @Autowired
    private OrganizationServiceImpl organizationServiceImpl;

    @Autowired
    private ClientServiceImpl clientServiceImpl;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @PostMapping
    @ResponseBody
    public ResponseEntity<ClientOrder> newClientOrder(@RequestBody ClientOrder clientOrder) {
        if (clientOrder == null
                || clientOrder.getId() != null
                || clientOrder.getOrganization() == null
                || clientOrder.getClient() == null
                || clientOrder.getPositions() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Organization organization = clientOrder.getOrganization();
        if (organization.getId() == null
                || !organizationServiceImpl.existsById(organization.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Client client = clientOrder.getClient();
        if (client.getId() == null
                || !clientServiceImpl.existsById(client.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<ClientOrderPosition> positions = clientOrder.getPositions();
        if (positions.isEmpty()
                || !productServiceImpl.existsAllByIdIn(positions.stream().map(p -> p.getProduct().getId()).collect(Collectors.toList()))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ClientOrder result = clientOrderServiceImpl.create(clientOrder);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<ClientOrder>> read() {
        Iterable<ClientOrder> result = clientOrderServiceImpl.read();
        return (StreamSupport.stream(result.spliterator(), false).count() == 0 ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(result, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientOrder> read(@PathVariable("id") Long id) {
        if (id == null
                || id < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return (!clientOrderServiceImpl.existsById(id) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(clientOrderServiceImpl.read(id), HttpStatus.OK));
    }

    @PutMapping
    public ResponseEntity<ClientOrder> updateObject(@RequestBody ClientOrder clientOrder) {
        if (clientOrder == null
                || clientOrder.getId() == null
                || clientOrder.getOrganization() == null
                || clientOrder.getClient() == null
                || clientOrder.getPositions() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!clientOrderServiceImpl.existsById(clientOrder.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ClientOrder result = clientOrderServiceImpl.updateObject(clientOrder);
        return result == null ? new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED) : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<ClientOrder> updateObjectParams(@RequestBody ClientOrder clientOrder) {
        if (clientOrder == null
                || clientOrder.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!clientOrderServiceImpl.existsById(clientOrder.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ClientOrder result = clientOrderServiceImpl.updateObjectParams(clientOrder);
        return result == null ? new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED) : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ClientOrder> delete(@RequestBody ClientOrder clientOrder) {
        if (clientOrder == null
                || clientOrder.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!clientOrderServiceImpl.existsById(clientOrder.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        clientOrderServiceImpl.delete(clientOrder);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}