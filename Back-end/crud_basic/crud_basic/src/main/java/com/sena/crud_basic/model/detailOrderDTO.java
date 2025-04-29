package com.sena.crud_basic.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "detailOrder")
public class detailOrderDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_DetailOrder", nullable = false)
    private int id_DetailOrder;

    @Column(name = "amount", nullable = false)
    private String amount;


    @ManyToOne
    @JoinColumn(name = "id_Order")
    private ordersDTO order;

    @ManyToOne
    @JoinColumn(name = "id_Product")
    private productsDTO products;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public ordersDTO getOrder() {
        return order;
    }

    public void setOrder(ordersDTO order) {
        this.order = order;
    }

    public productsDTO getProducts() {
        return products;
    }

    public void setProducts(productsDTO products) {
        this.products = products;
    }
}
