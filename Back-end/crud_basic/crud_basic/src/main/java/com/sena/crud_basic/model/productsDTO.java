package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "products")
public class productsDTO {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private int id_products;

@Column(name = "name",nullable = false,length =100)
private String name;

@Column(name = "price",nullable = false)
private double price;

@Column(name = "description", nullable = false, length = 50)
private String description;

public int getId_products() {
    return id_products;
}

public void setId_products(int id_products) {
    this.id_products = id_products;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public double getPrice() {
    return price;
}

public void setPrice(double price) {
    this.price = price;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}


}
