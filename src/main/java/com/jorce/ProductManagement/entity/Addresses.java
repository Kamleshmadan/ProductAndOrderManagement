package com.jorce.ProductManagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Addresses")
public class Addresses {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
