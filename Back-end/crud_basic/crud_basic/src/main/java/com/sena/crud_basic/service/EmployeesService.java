package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.employeesDTO;
import com.sena.crud_basic.repository.IEmployeesRepository;

@Service
public class EmployeesService {

    @Autowired
    private IEmployeesRepository employeesRepository;

    public boolean save(employeesDTO employees) {
        employeesRepository.save(employees);
        return true;
    }
}