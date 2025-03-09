package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "detailOrder")
public class detailOrderDTO {

@Column(name = "amount", nullable = false)
private String amount;

@Column(name =  "Subtotal", nullable = false)
private String Subtotal;

@ManyToOne
@JoinColumn(name = "id_Order")
private ordersDTO order;

@ManyToOne
@JoinColumn(name = "id_Product")
private productsDTO products;

public String getAmount() {
    return amount;
}

public void setAmount(String amount) {
    this.amount = amount;
}

public String getSubtotal() {
    return Subtotal;
}

public void setSubtotal(String subtotal) {
    Subtotal = subtotal;
}

public ordersDTO getOrder() {
    return order;
}

public void setOrder(ordersDTO order) {
    this.order = order;
}

public productsDTO getProducts() {
    return products;
}

public void setProducts(productsDTO products) {
    this.products = products;
}



}
