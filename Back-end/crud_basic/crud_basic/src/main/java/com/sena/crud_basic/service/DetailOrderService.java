package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.detailOrderDTO;
import com.sena.crud_basic.model.ordersDTO;
import com.sena.crud_basic.model.productsDTO;
import com.sena.crud_basic.repository.IDetailOrderRepository;  // Asegúrate de tener este repositorio
import com.sena.crud_basic.repository.IOrdersRepository;
import com.sena.crud_basic.repository.IProductsRepository;

@Service
public class DetailOrderService {

    @Autowired
    private IDetailOrderRepository iDetailOrderRepository;

    @Autowired
    private IOrdersRepository IOrdersRepository;  // Repositorio de Orders

    @Autowired
    private IProductsRepository IProductsRepository;  // Repositorio de Products

    // Obtener todos los detalles de pedido
    public List<detailOrderDTO> findAllDetailOrderRepository() {
        return iDetailOrderRepository.findAll();
    }

    // Obtener detalle de pedido por ID
    public Optional<detailOrderDTO> findByIdDetailOrderRepository(int id) {
        return iDetailOrderRepository.findById(id);
    }

    public detailOrderDTO save(detailOrderDTO detailOrder) {
        ordersDTO order = IOrdersRepository.findById(detailOrder.getOrder().getId_orders())
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    
        productsDTO product = IProductsRepository.findById(detailOrder.getProducts().getId_products())
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    
        detailOrder.setOrder(order);
        detailOrder.setProducts(product);
    
        return iDetailOrderRepository.save(detailOrder); // <- devolver el objeto guardado
    }
    

    // Actualizar un detalle de pedido
    public void update(int id, detailOrderDTO detailOrderUpdate) {
        var detailOrder = findByIdDetailOrderRepository(id);
        if (detailOrder.isPresent()) {
            detailOrder.get().setAmount(detailOrderUpdate.getAmount());
            
            // Aquí también se podrían actualizar las referencias de Order y Product si se requiere
            iDetailOrderRepository.save(detailOrder.get());
        }
    }

    // Eliminar un detalle de pedido
    public void delete(int id) {
        var detailOrder = findByIdDetailOrderRepository(id);
        if (detailOrder.isPresent()) {
            iDetailOrderRepository.delete(detailOrder.get());
        }
    }
}
