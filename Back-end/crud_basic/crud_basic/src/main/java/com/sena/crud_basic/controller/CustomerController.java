package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.customerDTO;
import com.sena.crud_basic.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //método para crear un registro POST
    @PostMapping("/")
    public String registerCustomer(@RequestBody customerDTO customer) {

        customerService.save(customer);
        return "registrado ok";
    }
    





}
