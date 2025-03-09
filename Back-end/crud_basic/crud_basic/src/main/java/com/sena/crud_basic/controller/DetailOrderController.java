package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.detailOrderDTO;
import com.sena.crud_basic.service.DetailOrderService;

@RestController
public class DetailOrderController {

    @Autowired
    private DetailOrderService detailOrderService;

    //método para crear un registro POST
    @PostMapping("/")
    public String registerDetailOrder(@RequestBody detailOrderDTO detailOrder) {

        detailOrderService.save(detailOrder);
        return "registrado ok";
    }
    
}
