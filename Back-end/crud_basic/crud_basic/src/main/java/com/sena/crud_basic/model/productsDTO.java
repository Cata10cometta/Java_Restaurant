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

@Column(name = "first_name",nullable = false,length =100)
private String first_name;

@Column(name = "price",nullable = false)
private String price;

@Column(name = "category", nullable = false, length = 50)
private String category;

public int getId_products() {
    return id_products;
}

public void setId_products(int id_products) {
    this.id_products = id_products;
}

public String getFirst_name() {
    return first_name;
}

public void setFirst_name(String first_name) {
    this.first_name = first_name;
}

public String getPrice() {
    return price;
}

public void setPrice(String price) {
    this.price = price;
}

public String getCategory() {
    return category;
}

public void setCategory(String category) {
    this.category = category;
}


}
