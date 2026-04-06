package com.jorce.ProductManagement.service;

import com.jorce.ProductManagement.Repository.CartItemRepository;
import com.jorce.ProductManagement.Repository.CartRepository;
import com.jorce.ProductManagement.entity.Cart;
import com.jorce.ProductManagement.entity.CartItems;
import com.jorce.ProductManagement.entity.Product;
import com.jorce.ProductManagement.entity.User;
import com.jorce.ProductManagement.exception.CartItemNotFoundException;
import com.jorce.ProductManagement.exception.CartNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {


    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;

    public CartService(CartItemRepository cartItemRepository, CartRepository cartRepository, UserService userService, ProductService productService) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public Cart addToCart(String userName, int productId, int quantity) {
        User user = userService.getUserByUsername(userName);
        Product product = productService.getProductById(productId);
        if (!product.isEnable()) {
            throw new IllegalStateException("Product is disabled");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be > 0");
        }

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> cartRepository.save(new Cart(user)));
        CartItems item = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId)
                .orElseGet(() -> new CartItems(cart, product, 0));
        item.setQuantity(item.getQuantity() + quantity);
        CartItems newCartItem = cartItemRepository.save(item);
        return cart;
    }

    public CartItems updateCartItem(String userName, int productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be > 0");
        }
        User user = userService.getUserByUsername(userName);
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new CartNotFoundException("Cart not found for userName: " + userName));
        CartItems item = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item not found for productID: " + productId));
        Product product = productService.getProductById(productId);

        if (!product.isEnable()) {
            throw new IllegalStateException("Product is disabled");
        }
        item.setQuantity(quantity);
        return cartItemRepository.save(item);
    }

    public void removeCartItem(String userName, int productId) {
        User user = userService.getUserByUsername(userName);
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new CartNotFoundException("Cart not found for userName: " + userName));
        CartItems item = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item not found for productID: " + productId));
        cartItemRepository.delete(item);
    }


    public Cart getCartByUserId(int userId) {
        return cartRepository.findByUserId(userId).orElseThrow(() ->
                new CartNotFoundException("Cart not found for userId: " + userId));
    }

    public List<CartItems> getCartItemsByCartId(int cartId) {
        List<CartItems> cartItems = cartItemRepository.findByCartId(cartId);
        if (cartItems.isEmpty()) {
            throw new CartItemNotFoundException("Cart item not found for cartId: " + cartId);
        }
        return cartItems;
    }

    public void deleteCartItemsByCartId(int cartId) {
        cartRepository.deleteById(cartId);
    }
}
