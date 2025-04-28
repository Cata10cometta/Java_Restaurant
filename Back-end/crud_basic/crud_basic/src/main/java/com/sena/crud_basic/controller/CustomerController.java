package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.customerDTO;
import com.sena.crud_basic.service.CustomerService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllCustomer() {
        var ListCustomer = customerService.findAllCustomerRepository();
        return new ResponseEntity<Object>(ListCustomer, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdCustomer(@PathVariable int id) {
        var customer = customerService.findByIdCustomerRepository(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);  
    }

    //método para crear un registro POST
    @PostMapping("/")
    public String save(@RequestBody customerDTO customer) {
        customerService.save(customer);
        return "registrado ok";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody customerDTO customer) {
        customerService.update(id, customer);
        return "Update ok";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        customerService.delete(id);
        return "Delete ok";
    


    }


}
