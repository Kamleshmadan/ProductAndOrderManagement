package com.jorce.ProductManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "order_items")
public class OrderItems {

    public OrderItems() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @Min(value = 0, message = "Quantity cannot be negative")
    int quantity;

    @Positive
    double unitPrice;

    @Positive
    double totalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Product getProducts() {
        return product;
    }

    public void setProducts(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
