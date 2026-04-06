package com.jorce.ProductManagement.service;

import com.jorce.ProductManagement.Repository.OrderRepository;
import com.jorce.ProductManagement.entity.*;
import com.jorce.ProductManagement.exception.InsufficientInventoryException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository, UserService userService, ProductService productService, CartService cartService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public Orders placeOrder(String userName) {
        User user = userService.getUserByUsername(userName);
        Cart cart = cartService.getCartByUserId(user.getId());
        List<CartItems> cartItems = cartService.getCartItemsByCartId(cart.getId());
        double total = 0;

        List<Product> productsToLock = new ArrayList<>();
        for (CartItems ci : cartItems) {
            Product p = productService.getProductById(ci.getProduct().getId());
            productsToLock.add(p);
            if (!p.isEnable()) {
                throw new IllegalStateException("Product is disabled: " + p.getId());
            }
            if (p.getQuantity() < ci.getQuantity()) {
                throw new InsufficientInventoryException(
                        "Insufficient inventory for productId=" + p.getId()
                );
            }
            double lineTotal = p.getPrice() * ci.getQuantity();
            total = total + lineTotal;
        }
        Orders order = new Orders();
        order.setUser(user);
        order.setTotalAmount(total);
        order.setStatus("PLACED");
        List<OrderItems> orderItems = new ArrayList<>();
        for (CartItems ci : cartItems) {
            Product p = productService.getProductById(ci.getProduct().getId());
            p.setQuantity(p.getQuantity() - ci.getQuantity());
            productService.addProduct(p);
            OrderItems oi = new OrderItems();
            oi.setOrder(order);
            oi.setProducts(p);
            oi.setUnitPrice(p.getPrice());
            oi.setQuantity(ci.getQuantity());
            oi.setTotalPrice(p.getPrice() * ci.getQuantity());
            orderItems.add(oi);
        }
        order.setOrderItems(orderItems);
        Orders savedOrder = orderRepository.save(order);
        return savedOrder;
    }
}
