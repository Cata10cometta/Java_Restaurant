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

import com.sena.crud_basic.model.detailOrderDTO;
import com.sena.crud_basic.service.DetailOrderService;

@RestController
@RequestMapping("/api/v1/detailOrder")
public class DetailOrderController {

    @Autowired
    private DetailOrderService detailOrderService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllDetailOrder() {
        var ListDetailOrder = detailOrderService.findAllDetailOrderRepository();
        return new ResponseEntity<Object>(ListDetailOrder, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdDetailOrder(@PathVariable int id) {
        var detailOrder = detailOrderService.findByIdDetailOrderRepository(id);
        return new ResponseEntity<>(detailOrder, HttpStatus.OK);  
    }

    //método para crear un registro POST
    @PostMapping("/")
    public String save(@RequestBody detailOrderDTO detailOrder) {
        detailOrderService.save(detailOrder);
        return "registrado ok";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody detailOrderDTO detailOrder) {
        detailOrderService.update(id, detailOrder);
        return "Update ok";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        detailOrderService.delete(id);
        return "Delete ok";
    
}
}
