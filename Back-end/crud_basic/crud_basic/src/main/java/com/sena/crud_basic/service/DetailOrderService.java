package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.detailOrderDTO;
import com.sena.crud_basic.repository.IDetailOrderRepository;

@Service
public class DetailOrderService {

    @Autowired
    private IDetailOrderRepository iDetailOrderRepository;

    public boolean save(detailOrderDTO detailOrder) {
        iDetailOrderRepository.save(detailOrder);
        return true;
    }

}
