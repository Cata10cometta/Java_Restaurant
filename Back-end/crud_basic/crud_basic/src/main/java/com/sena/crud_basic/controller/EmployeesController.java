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

import com.sena.crud_basic.model.employeesDTO;
import com.sena.crud_basic.service.EmployeesService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeesController {
    
    @Autowired
    private EmployeesService employeesService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllEmployees() {
        var ListEmployees = employeesService.findAllEmployeesRepository();
        return new ResponseEntity<Object>(ListEmployees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdEmployees(int id) {
        var employee = employeesService.findByIdEmployeesRepository(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);  
    }

    @PostMapping("/")
    public String save(@RequestBody employeesDTO employees) {
        employeesService.save(employees);
        return "registrado ok";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody employeesDTO employee) {
        employeesService.update(id, employee);
        return "Update ok";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        employeesService.delete(id);
        return "Delete ok";
    }
}