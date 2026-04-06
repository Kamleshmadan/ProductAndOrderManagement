package com.jorce.ProductManagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
}
