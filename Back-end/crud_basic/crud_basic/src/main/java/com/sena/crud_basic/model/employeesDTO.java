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

@Column(name = "first_name", nullable = false,length = 30)
private String first_name;

@Column(name = "post",nullable = false,length = 50)
private String post;

public int getId_employees() {
    return id_employees;
}

public void setId_employees(int id_employees) {
    this.id_employees = id_employees;
}

public String getFirst_name() {
    return first_name;
}

public void setFirst_name(String first_name) {
    this.first_name = first_name;
}

public String getPost() {
    return post;
}

public void setPost(String post) {
    this.post = post;
}


}
