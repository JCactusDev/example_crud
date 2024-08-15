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
@Table(name = "organization")
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "tax_number")
    private String taxNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "organization")
    private List<ClientOrder> clientOrders;

    public Organization() {
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

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
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
        Organization other = (Organization) otherObject;

        // Проверка хранимых значений в свойствах объекта
        return Objects.equals(name, other.name)
                && Objects.equals(taxNumber, other.taxNumber);
    }

    @Override
    public int hashCode() {
        return 31 * ((id == null) ? 0 : id.hashCode())
                + 31 * ((name == null) ? 0 : name.hashCode())
                + 31 * ((taxNumber == null) ? 0 : taxNumber.hashCode());
    }

    @Override
    public String toString() {
        return "Organization [id=" + id + ", name=" + name + ", taxNumber=" + taxNumber + "]";
    }

    @Override
    public Organization clone() throws CloneNotSupportedException {
        Organization cloneObject;
        try {
            cloneObject = (Organization) super.clone();
        } catch (CloneNotSupportedException e) {
            cloneObject = new Organization();
        }
        cloneObject.name = name;
        cloneObject.taxNumber = taxNumber;
        return cloneObject;
    }

}
