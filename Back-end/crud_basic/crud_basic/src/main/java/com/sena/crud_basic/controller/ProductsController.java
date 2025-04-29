package com.sena.crud_basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.model.productsDTO;
import com.sena.crud_basic.service.ProductsService;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*")  // Permite solicitudes de cualquier origen
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    // Obtener todos los productos
    @GetMapping("/")
    public ResponseEntity<Object> findAllProducts() {
        List<productsDTO> listProducts = productsService.findAllProductsRepository();
        return ResponseEntity.ok(listProducts);
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdProducts(@PathVariable int id) {
        var product = productsService.findByIdProductsRepository(id);
        return ResponseEntity.ok(product);
    }

    // Registrar un nuevo producto
    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody productsDTO products) {
        productsService.save(products);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto registrado correctamente");
    }

    // Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody productsDTO product) {
        productsService.update(id, product);
        return ResponseEntity.ok("Producto actualizado correctamente");
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
    try {
        productsService.delete(id);
        return ResponseEntity.ok("Producto eliminado correctamente");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No se pudo eliminar el producto. Puede estar relacionado con otro registro.");
    }
}

}
