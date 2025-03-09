package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.ordersDTO;
import com.sena.crud_basic.service.OrdersService;

@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    //método para crear un registro POST
    @PostMapping("/")
    public String registerOrder(@RequestBody ordersDTO orders) {

        ordersService.save(orders);
        return "registrado ok";
    }
    
}
