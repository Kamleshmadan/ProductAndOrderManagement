package com.jorce.ProductManagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Cart")
public class Cart {

    public Cart() {
    }

    public Cart(User user) {
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
