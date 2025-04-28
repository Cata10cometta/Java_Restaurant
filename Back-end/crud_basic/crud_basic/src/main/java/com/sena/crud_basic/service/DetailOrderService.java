package com.sena.crud_basic.service;

import java.lang.classfile.ClassFile.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.detailOrderDTO;
import com.sena.crud_basic.repository.IDetailOrderRepository;

@Service
public class DetailOrderService {

    @Autowired
    private IDetailOrderRepository iDetailOrderRepository;

    public List<detailOrderDTO> findAllDetailOrderRepository() {
        return iDetailOrderRepository.findAll();
    }

    public Optional<detailOrderDTO> findByIdDetailOrderRepository(int id) {
        return iDetailOrderRepository.findById(id);
    }

    public void save(detailOrderDTO detailOrder) {
        iDetailOrderRepository.save(detailOrder);
        
    }

    public void update(int id, detailOrderDTO detailOrderUpdate) {
        var detailOrder = findByIdDetailOrderRepository(id);
        if (detailOrder.isPresent()) {
            detailOrder.get().setAmount(detailOrderUpdate.getAmount());
            detailOrder.get().setSubtotal(detailOrderUpdate.getSubtotal());

            iDetailOrderRepository.save(detailOrder.get());
        }
    }
    public void delete(int id) {
        var detailOrder = findByIdDetailOrderRepository(id);
        if (detailOrder.isPresent()) {
            iDetailOrderRepository.delete(detailOrder.get());
        }
    }

    

}
