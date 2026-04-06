package com.jorce.ProductManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "user_roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;

    @NotBlank(message = "Role cannot be null")
    @Column(name = "role_name", nullable = false, unique = true)
    String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
