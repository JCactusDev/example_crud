package com.github.jcactusdev.example_crud.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "client_order")
public class ClientOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "clientOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ClientOrderPosition> positions;

    public ClientOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ClientOrderPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<ClientOrderPosition> positions) {
        this.positions = positions;
    }

    public void addPosition(ClientOrderPosition position) {
        positions.add(position);
    }

    public void addPositions(List<ClientOrderPosition> positions) {
        positions.addAll(positions);
    }

    @Override
    public boolean equals(Object otherObject) {
        // Проверка объектов на идентичность
        if (this == otherObject) {
            return true;
        }

        // Проверка явного параметра == null
        if (otherObject == null) {
            return false;
        }

        // Проверка совпадения классов
        if (this.getClass() != otherObject.getClass()) {
            return false;
        }

        // Приведение otherObject к типу текущего класа
        ClientOrder other = (ClientOrder) otherObject;

        // Проверка хранимых значений в свойствах объекта
        return Objects.equals(organization, other.organization)
                && Objects.equals(client, other.client)
                && Arrays.equals(positions.toArray(), other.positions.toArray());
    }

    @Override
    public int hashCode() {
        return 11 * ((id == null) ? 0 : id.hashCode())
                + 5 * ((organization == null) ? 0 : organization.hashCode())
                + 5 * ((client == null) ? 0 : client.hashCode())
                + 10 * ((positions == null) ? 0 : positions.hashCode());
    }

    @Override
    public String toString() {
        return "ClientOrder [id=" + id + ", organization=" + organization.toString() + ", client=" + client.toString() + ", positions="
                + positions.toString() + "]";
    }

    @Override
    public ClientOrder clone() throws CloneNotSupportedException {
        ClientOrder cloneObject;
        try {
            cloneObject = (ClientOrder) super.clone();
        } catch (CloneNotSupportedException e) {
            cloneObject = new ClientOrder();
        }
        cloneObject.organization = (Organization) organization.clone();
        cloneObject.client = (Client) client.clone();
        cloneObject.positions = new ArrayList<>();
        for (ClientOrderPosition position : positions) {
            cloneObject.positions.add(position.clone());
        }
        return cloneObject;
    }

}
