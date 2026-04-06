package com.jorce.ProductManagement.controller;

import com.jorce.ProductManagement.entity.Cart;
import com.jorce.ProductManagement.entity.CartItems;
import com.jorce.ProductManagement.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    @PostMapping("/addItem")
    public ResponseEntity<Cart> addToCart(
            @RequestParam String userName,
            @RequestParam int productId,
            @RequestParam int quantity
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cartService.addToCart(userName, productId, quantity));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    @PutMapping("/updateItems")
    public CartItems updateCartItem(
            @RequestParam String userName,
            @RequestParam int productId,
            @RequestParam int quantity
    ) {
        return cartService.updateCartItem(userName, productId, quantity);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    @DeleteMapping("/removeItems")
    public ResponseEntity<String> removeCartItem(
            @RequestParam String userName,
            @RequestParam int productId
    ) {
        cartService.removeCartItem(userName, productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Removed product Id: " + productId + " for userName: " + userName);
    }
}
