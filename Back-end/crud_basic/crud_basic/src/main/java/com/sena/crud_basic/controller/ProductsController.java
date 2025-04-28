package com.sena.crud_basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.model.productsDTO;
import com.sena.crud_basic.repository.IProductsRepository;
import com.sena.crud_basic.service.ProductsService;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*")  // <--- AGREGA ESTO para permitir solicitudes del navegador
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllProducts() {
        var ListProducts = productsService.findAllProductsRepository();
        return ResponseEntity.ok(ListProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdProducts(@PathVariable int id) {
        var product = productsService.findByIdProductsRepository(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody productsDTO products) {
        productsService.save(products);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto registrado correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody productsDTO product) {
        productsService.update(id, product);
        return ResponseEntity.ok("Producto actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        productsService.delete(id);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }

    
}


