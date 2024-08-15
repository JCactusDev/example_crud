package com.github.jcactusdev.example_crud.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    public Product() {
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
        Product other = (Product) otherObject;

        // Проверка хранимых значений в свойствах объекта
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return 31 * ((id == null) ? 0 : id.hashCode())
                + 31 * ((name == null) ? 0 : name.hashCode());
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + "]";
    }

    @Override
    public Product clone() throws CloneNotSupportedException {
        Product cloneObject;
        try {
            cloneObject = (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            cloneObject = new Product();
        }
        cloneObject.name = name;
        return cloneObject;
    }

}
