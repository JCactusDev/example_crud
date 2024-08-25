package com.github.jcactusdev.example_crud.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "client_order")
public class ClientOrder implements Serializable {
    private static final long serialVersionUID = 1L;

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

    @NotNull
    @Column(name = "order_status")
    private ClientOrderStatus status;

    @OneToMany(mappedBy = "clientOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ClientOrderPosition> positions;

    public ClientOrder() {
        positions = new ArrayList<>();
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

    public ClientOrderStatus getStatus() {
        return status;
    }

    public void setStatus(ClientOrderStatus status) {
        this.status = status;
    }

    public List<ClientOrderPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<ClientOrderPosition> positions) {
        this.positions.clear();
        positions.forEach(this::addPosition);
    }

    public void addPosition(ClientOrderPosition position) {
        position.setClientOrder(this);
        positions.add(position);
    }

    public void removePosition(ClientOrderPosition position) {
        position.setClientOrder(null);
        positions.remove(position);
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
        return Objects.equals(id, other.id)
                && Objects.equals(organization, other.organization)
                && Objects.equals(client, other.client)
                && Objects.equals(status, other.status)
                && Objects.equals(positions, other.positions);
    }

    @Override
    public int hashCode() {
        return 31 * ((id == null) ? 0 : id.hashCode())
                + 31 * ((organization == null) ? 0 : organization.hashCode())
                + 31 * ((client == null) ? 0 : client.hashCode())
                + 31 * ((status == null) ? 0 : status.hashCode())
                + 31 * ((positions == null) ? 0 : positions.hashCode());
    }

    @Override
    public String toString() {
        return "ClientOrder [id=" + id + ", organization=" + organization.toString() + ", client=" + client.toString() + ", status=" + status.name() + ", positions="
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
        cloneObject.organization = organization.clone();
        cloneObject.client = client.clone();
        cloneObject.status = status;
        cloneObject.positions = new ArrayList<>();
        for (ClientOrderPosition position : positions) {
            cloneObject.positions.add(position.clone());
        }
        return cloneObject;
    }
}
