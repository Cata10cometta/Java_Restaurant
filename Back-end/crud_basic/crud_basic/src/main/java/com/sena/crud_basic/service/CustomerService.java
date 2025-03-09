package com.sena.crud_basic.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.model.customerDTO;
import com.sena.crud_basic.repository.ICustomerRepository;

/*
 * Agregamos la anotación bean @Service 
 * para indicar que el archivo es un Servicio
 */
@Service
public class CustomerService {
    
    //Se realiza la concexión con el repositorio
    @Autowired
    private ICustomerRepository ICustomerRepository;

    /*
     * crear
     * eliminar
     * actuazalizar
     * leer lista completa
     * leer el cliente por id
     * adicional los requeridos
     */

     public boolean save (customerDTO customer){
        /*
         * if(customer.getId==0)register o create 
         * else update
         */
        
        ICustomerRepository.save(customer);
        return true;
     }


}
