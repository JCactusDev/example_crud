package com.github.jcactusdev.example_crud.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<ClientOrder> clientOrders;

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<ClientOrder> getClientOrders() {
        return clientOrders;
    }

    @JsonIgnore
    public void setClientOrders(List<ClientOrder> clientOrders) {
        this.clientOrders = clientOrders;
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
        Client other = (Client) otherObject;

        // Проверка хранимых значений в свойствах объекта
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return 11 * ((this.id == null) ? 0 : this.id.hashCode())
                + 20 * ((this.name == null) ? 0 : this.name.hashCode());
    }

    @Override
    public String toString() {
        return "Client [id=" + this.id + ", name=" + this.name + "]";
    }

    @Override
    public Client clone() throws CloneNotSupportedException {
        Client cloneObject;
        try {
            cloneObject = (Client) super.clone();
        } catch (CloneNotSupportedException e) {
            cloneObject = new Client();
        }
        cloneObject.name = this.name;
        return cloneObject;
    }

}
