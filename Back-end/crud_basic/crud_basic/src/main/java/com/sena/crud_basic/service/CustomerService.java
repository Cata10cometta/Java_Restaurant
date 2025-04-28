package com.sena.crud_basic.service;


import java.lang.classfile.ClassFile.Option;
import java.util.List;
import java.util.Optional;

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

     public List<customerDTO> findAllCustomerRepository() {
        return ICustomerRepository.findAll();
     }

     public Optional<customerDTO> findByIdCustomerRepository(int id) {
        return ICustomerRepository.findById(id);
     }


     public void save (customerDTO customer){
        /*
         * if(customer.getId==0)register o create 
         * else update
         */
        
        ICustomerRepository.save(customer);
        
     }

       public void update(int id, customerDTO customerUpdate) {
         var customer = findByIdCustomerRepository(id);
         if (customer.isPresent()){
               customer.get().setName(customerUpdate.getName());
               customer.get().setPhone(customerUpdate.getPhone());
               customer.get().setEmail(customerUpdate.getEmail());
               ICustomerRepository.save(customer.get());
         }
       }
         public void delete(int id) {
            var customer = findByIdCustomerRepository(id);
            if (customer.isPresent()){
                  ICustomerRepository.delete(customer.get());
            }
         }


}
