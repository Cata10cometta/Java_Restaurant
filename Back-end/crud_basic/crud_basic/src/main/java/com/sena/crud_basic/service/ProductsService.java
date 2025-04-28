package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.productsDTO;
import com.sena.crud_basic.repository.IProductsRepository;

@Service
public class ProductsService {

    @Autowired
    private IProductsRepository IProductsRepository;

    public List<productsDTO> findAllProductsRepository() {
        return IProductsRepository.findAll();
    }

    public Optional<productsDTO> findByIdProductsRepository(int id) {
        return IProductsRepository.findById(id);
    }

    public void save (productsDTO products){
        IProductsRepository.save(products);
        
    }
    
    public void update(int id, productsDTO productUpdate) {
        var product = findByIdProductsRepository(id);
        if (product.isPresent()){
            product.get().setName(productUpdate.getName());
            product.get().setDescription(productUpdate.getDescription());
            product.get().setPrice(productUpdate.getPrice());
            IProductsRepository.save(product.get());
        }
    }
    public void delete(int id) {
        var product = findByIdProductsRepository(id);
        if (product.isPresent()){
            IProductsRepository.delete(product.get());
        }
    }
}
