package com.github.jcactusdev.example_crud.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "organization")
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Column(name = "full_name")
    private String fullName;

    @NotNull
    @Column(name = "short_name")
    private String shortName;

    @Column(name = "international_name")
    private String internationalName;

    @NotNull
    @Column(name = "type")
    private OrganizationType type;

    @NotNull
    @Column(name = "tax_number")
    private String taxNumber;

    @NotNull
    @Column(name = "reg_number")
    private String regNumber;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "reg_date")
    private LocalDate regDate;

    @Column(name = "description")
    private String description;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getInternationalName() {
        return internationalName;
    }

    public void setInternationalName(String internationalName) {
        this.internationalName = internationalName;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return Objects.equals(id, other.id)
                && Objects.equals(name, other.name)
                && Objects.equals(fullName, other.fullName)
                && Objects.equals(shortName, other.shortName)
                && Objects.equals(internationalName, other.internationalName)
                && Objects.equals(type, other.type)
                && Objects.equals(taxNumber, other.taxNumber)
                && Objects.equals(regNumber, other.regNumber)
                && Objects.equals(regDate, other.regDate)
                && Objects.equals(description, other.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fullName, shortName, internationalName, type, taxNumber, regNumber, regDate, description);
    }

    @Override
    public String toString() {
        return String.format("Organization [id=%s, name=%s, fullName=%s, shortName=%s, internationalName=%s, type=%s, taxNumber=%s, regNumber=%s, regDate=%s, description=%s]",
                id, name, fullName, shortName, internationalName, type, taxNumber, regNumber, regDate, description
        );
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
        cloneObject.fullName = fullName;
        cloneObject.shortName = shortName;
        cloneObject.internationalName = internationalName;
        cloneObject.type = type;
        cloneObject.taxNumber = taxNumber;
        cloneObject.regNumber = regNumber;
        cloneObject.regDate = regDate;
        cloneObject.description = description;
        return cloneObject;
    }

}
