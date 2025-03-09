package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.productsDTO;
import com.sena.crud_basic.repository.IProductsRepository;

@Service
public class ProductsService {

    @Autowired
    private IProductsRepository IProductsRepository;

    public boolean save (productsDTO products){
        IProductsRepository.save(products);
        return true;
    }
    
}
