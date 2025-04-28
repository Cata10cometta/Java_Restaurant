package com.sena.crud_basic.service;

import java.lang.classfile.ClassFile.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.employeesDTO;
import com.sena.crud_basic.repository.IEmployeesRepository;

@Service
public class EmployeesService {

    @Autowired
    private IEmployeesRepository employeesRepository;

    public List<employeesDTO> findAllEmployeesRepository() {
        return employeesRepository.findAll();
    }

    public Optional<employeesDTO> findByIdEmployeesRepository(int id) {
        return employeesRepository.findById(id);
    }

    public void save(employeesDTO employees) {
        employeesRepository.save(employees);   
    }

    public void update(int id, employeesDTO employeeUpdate) {
        var employee = findByIdEmployeesRepository(id);
        if (employee.isPresent()){
            employee.get().setName(employeeUpdate.getName());
            employee.get().setRole(employeeUpdate.getRole());
            employee.get().setEmail(employeeUpdate.getEmail());
            employeesRepository.save(employee.get());
        }
    }
    public void delete(int id) {
        var employee = findByIdEmployeesRepository(id);
        if (employee.isPresent()){
            employeesRepository.delete(employee.get());
        }
    }
}