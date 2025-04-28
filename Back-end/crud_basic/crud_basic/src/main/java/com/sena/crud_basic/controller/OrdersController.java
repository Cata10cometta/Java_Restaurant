package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.ordersDTO;
import com.sena.crud_basic.service.OrdersService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllOrders() {
        var ListOrders = ordersService.findAllOrdersRepository();
        return new ResponseEntity<Object>(ListOrders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdOrders(@PathVariable int id) {
        var order = ordersService.findByIdOrdersRepository(id);
        return new ResponseEntity<>(order, HttpStatus.OK);  
    }

    //método para crear un registro POST
    @PostMapping("/")
    public String save(@RequestBody ordersDTO orders) {
        ordersService.save(orders);
        return "registrado ok";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody ordersDTO orders) {
        ordersService.update(id, orders);
        return "Update ok";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        ordersService.delete(id);
        return "Delete ok";
    }
    
}
