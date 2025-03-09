package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.productsDTO;
import com.sena.crud_basic.service.ProductsService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ProductsController {

    @Autowired
    private  ProductsService productsService;
    
    @PostMapping("/")
    public String registerProducts(@RequestBody productsDTO products) {
        
        productsService.save(products);
        return "registrado ok";
        
    }
    
    
}
