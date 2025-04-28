package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
 * Para indicar que la clase es un modelo, se utiliza la anotación 
 * bean @Entity
 */
@Entity(name="customer")
public class customerDTO {
/*
 * DTO= Data transfer Object
 * las clases DTO contienen las entidades de la base de datos
 */
//id=PRIMARY KEY
//GeneratedValue= Auto incremental
//@Column=para indicar que el atributo es una columna
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="id")
private int id_customer;

@Column(name = "name",nullable = false,length = 30)
private String name;
//agregar la n cantidad de columnas
@Column(name = "phone",nullable = false, length = 15)
private String phone;

@Column(name = "email",nullable = false, length = 255)
private String email;

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public int getId_customer() {
    return id_customer;
}

public void setId_customer(int id_customer) {
    this.id_customer = id_customer;
}

public String getPhone() {
    return phone;
}

public void setPhone(String phone) {
    this.phone = phone;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

}


