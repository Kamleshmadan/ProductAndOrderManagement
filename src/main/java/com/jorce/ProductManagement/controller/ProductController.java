package com.jorce.ProductManagement.controller;

import com.jorce.ProductManagement.entity.Product;
import com.jorce.ProductManagement.service.CategoryService;
import com.jorce.ProductManagement.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    @GetMapping("/getProducts")
    public List<Product> fetchAllProducts() {
        return productService.getAllProducts();
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    @GetMapping("/getProductsByCategory")
    public List<Product> fetchProductsByCategory(@RequestParam int categoryId) {
        return productService.getProductByCategory(categoryId);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable int id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProductById(id, product));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PatchMapping("/{id}/price-quantity")
    public Product updatePriceQuantity(
            @PathVariable int id,
            @RequestParam double newPrice,
            @RequestParam int newQuantity
    ) {
        return productService.updatePriceQuantity(id, newPrice, newQuantity);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PatchMapping("/{id}/visibility")
    public Product updateVisibility(
            @PathVariable int id,
            @RequestParam boolean isVisible
    ) {
        return productService.updateVisibility(id, isVisible);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PutMapping("/assignCategory")
    public ResponseEntity<Product> assignCategory(@RequestParam int productId, @RequestParam int categoryId) {
        return ResponseEntity.ok(categoryService.assignCategory(productId, categoryId));
    }
}

