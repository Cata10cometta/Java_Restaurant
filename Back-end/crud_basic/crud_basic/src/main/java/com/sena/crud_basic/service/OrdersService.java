package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.ordersDTO;
import com.sena.crud_basic.repository.IOrdersRepository;

@Service
public class OrdersService {

    @Autowired
    private IOrdersRepository IOrdersRepository;

    public boolean save (ordersDTO orders){
        IOrdersRepository.save(orders);
        return true;
    }
    
}
