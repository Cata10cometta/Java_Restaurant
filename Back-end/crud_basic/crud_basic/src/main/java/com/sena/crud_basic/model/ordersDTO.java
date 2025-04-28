package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "orders")
public class ordersDTO {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private int id_orders;

@Column(name = "orderDate",nullable = false, length = 20)
private String orderDate;



//relación
@ManyToOne
@JoinColumn(name = "id_customer")
private customerDTO customer;

@ManyToOne
@JoinColumn(name = "id_employees")
private employeesDTO employees;

public int getId_orders() {
    return id_orders;
}

public void setId_orders(int id_orders) {
    this.id_orders = id_orders;
}

public String getOrderDate() {
    return orderDate;
}

public void setOrderDate(String orderDate) {
    this.orderDate = orderDate;
}

public customerDTO getCustomer() {
    return customer;
}

public void setCustomer(customerDTO customer) {
    this.customer = customer;
}

public employeesDTO getEmployees() {
    return employees;
}

public void setEmployees(employeesDTO employees) {
    this.employees = employees;
}

}
