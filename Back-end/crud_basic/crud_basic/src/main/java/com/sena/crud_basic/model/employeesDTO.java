package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "employees")
public class employeesDTO {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private int id_employees;

@Column(name = "name", nullable = false,length = 30)
private String name;

@Column(name = "role", nullable = false,length = 30)
private String role;

@Column(name = "email",nullable = false,length = 50)
private String email;

public int getId_employees() {
    return id_employees;
}

public void setId_employees(int id_employees) {
    this.id_employees = id_employees;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getRole() {
    return role;
}

public void setRole(String role) {
    this.role = role;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}


}
