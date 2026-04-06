package com.jorce.ProductManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "cart_items")
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    Cart cart;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    @Min(value = 0, message = "Quantity cannot be negative")
    int quantity;

    public CartItems() {
    }

    public CartItems(Cart cart, Product product, int quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
