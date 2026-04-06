package com.jorce.ProductManagement.service;

import com.jorce.ProductManagement.Repository.CategoryRepository;
import com.jorce.ProductManagement.Repository.ProductRepository;
import com.jorce.ProductManagement.entity.Category;
import com.jorce.ProductManagement.entity.Product;
import com.jorce.ProductManagement.exception.CategoryNotFoundException;
import com.jorce.ProductManagement.exception.ProductNotFoundException;
import com.jorce.ProductManagement.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("product not found for Id: " + id));
    }

    public List<Product> getProductByCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category not found for Id: " + categoryId));
        return productRepository.findByCategory(category).orElseThrow(() ->
                new ProductNotFoundException("product not found for categoryId: " + category));
    }

    public Product updateProductById(int productId, Product product) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(() ->
                new UserNotFoundException("product not found for product Id:" + productId));

        existingProduct.setProductName(product.getProductName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setEnable(product.isEnable());
        return productRepository.save(existingProduct);
    }

    public Product updateProductByName(Product product) {
        Product existingProduct = productRepository.findByProductName(product.getProductName()).orElseThrow(() ->
                new UserNotFoundException("product not found for product Id:" + product.getId()));

        existingProduct.setProductName(product.getProductName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setEnable(product.isEnable());
        return productRepository.save(existingProduct);
    }


    public Product updatePriceQuantity(int id, double newPrice, int newQuantity) {
        Product existingProduct = getProductById(id);
        existingProduct.setPrice(newPrice);
        existingProduct.setQuantity(newQuantity);
        return productRepository.save(existingProduct);
    }

    public Product updateVisibility(int id, boolean isVisible) {
        Product existingProduct = getProductById(id);
        existingProduct.setEnable(isVisible);
        return productRepository.save(existingProduct);
    }


}
