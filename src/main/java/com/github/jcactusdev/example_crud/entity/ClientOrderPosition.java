package com.github.jcactusdev.example_crud.entity;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "client_order_position")
public class ClientOrderPosition implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_order_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private ClientOrder clientOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "count")
    private double count;

    @Column(name = "price")
    private double price;

    public ClientOrderPosition() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientOrder getClientOrder() {
        return clientOrder;
    }

    public void setClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
        ClientOrderPosition other = (ClientOrderPosition) otherObject;

        // Проверка хранимых значений в свойствах объекта
        return Objects.equals(clientOrder, other.clientOrder)
                && Objects.equals(product, other.product)
                && count == other.count
                && price == other.price;
    }

    @Override
    public int hashCode() {
        return 31 * ((id == null) ? 0 : id.hashCode())
                + 31 * ((clientOrder == null) ? 0 : clientOrder.hashCode())
                + 31 * ((product == null) ? 0 : product.hashCode())
                + 31 * Double.valueOf(count).hashCode()
                + 31 * Double.valueOf(price).hashCode();
    }

    @Override
    public String toString() {
        return "ClientOrderPosition [id=" + id + ", clientOrder=" + clientOrder.toString() + ", product=" + product.toString() + ", count="
                + count + ", price=" + price + "]";
    }

    @Override
    public ClientOrderPosition clone() throws CloneNotSupportedException {
        ClientOrderPosition cloneObject;
        try {
            cloneObject = (ClientOrderPosition) super.clone();
        } catch (CloneNotSupportedException e) {
            cloneObject = new ClientOrderPosition();
        }
        cloneObject.clientOrder = clientOrder.clone();
        cloneObject.product = product.clone();
        cloneObject.count = count;
        cloneObject.price = price;
        return cloneObject;
    }
}
