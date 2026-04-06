package com.jorce.ProductManagement.controller;

import com.jorce.ProductManagement.entity.Orders;
import com.jorce.ProductManagement.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPER_ADMIN')")
    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@RequestParam String userName) {
        Orders order = orderService.placeOrder(userName);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Congratulations " + order.getUser().getUsername() + ", your order has been placed successfully.");
    }

}
