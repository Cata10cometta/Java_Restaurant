package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.model.employeesDTO;
import com.sena.crud_basic.service.EmployeesService;

@RestController
public class EmployeesController {
    
    @Autowired
    private EmployeesService employeesService;

    @PostMapping("/employees")
    public String registerEmployees(@RequestBody employeesDTO employees) {
        employeesService.save(employees);
        return "registrado ok";
    }
}