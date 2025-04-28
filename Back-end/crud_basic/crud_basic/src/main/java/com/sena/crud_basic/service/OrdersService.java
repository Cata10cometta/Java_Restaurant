package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.ordersDTO;
import com.sena.crud_basic.model.customerDTO;
import com.sena.crud_basic.model.employeesDTO;
import com.sena.crud_basic.repository.IOrdersRepository;
import com.sena.crud_basic.repository.ICustomerRepository;
import com.sena.crud_basic.repository.IEmployeesRepository;

@Service
public class OrdersService {

    @Autowired
    private IOrdersRepository ordersRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IEmployeesRepository employeesRepository;

    public List<ordersDTO> findAllOrdersRepository() {
        return ordersRepository.findAll();
    }

    public Optional<ordersDTO> findByIdOrdersRepository(int id) {
        return ordersRepository.findById(id);
    }

    // Crear pedido con customer y employees
    public void save(ordersDTO orders) {
        if (orders.getCustomer() != null && orders.getCustomer().getId_customer() != 0) {
            customerDTO customer = customerRepository.findById(orders.getCustomer().getId_customer())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            orders.setCustomer(customer);
        }
        
        if (orders.getEmployees() != null && orders.getEmployees().getId_employees() != 0) {
            employeesDTO employee = employeesRepository.findById(orders.getEmployees().getId_employees())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
            orders.setEmployees(employee);
        }

        ordersRepository.save(orders);
    }

    public void update(int id, ordersDTO ordersUpdate) {
        var ordersOptional = findByIdOrdersRepository(id);
        if (ordersOptional.isPresent()) {
            var order = ordersOptional.get();
            order.setOrderDate(ordersUpdate.getOrderDate());
           ;

            if (ordersUpdate.getCustomer() != null && ordersUpdate.getCustomer().getId_customer() != 0) {
                customerDTO customer = customerRepository.findById(ordersUpdate.getCustomer().getId_customer())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
                order.setCustomer(customer);
            }

            if (ordersUpdate.getEmployees() != null && ordersUpdate.getEmployees().getId_employees() != 0) {
                employeesDTO employee = employeesRepository.findById(ordersUpdate.getEmployees().getId_employees())
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
                order.setEmployees(employee);
            }

            ordersRepository.save(order);
        }
    }

    public void delete(int id) {
        var orders = findByIdOrdersRepository(id);
        if (orders.isPresent()) {
            ordersRepository.delete(orders.get());
        }
    }
}
