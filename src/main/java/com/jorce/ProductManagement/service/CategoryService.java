package com.jorce.ProductManagement.service;

import com.jorce.ProductManagement.Repository.CategoryRepository;
import com.jorce.ProductManagement.entity.Category;
import com.jorce.ProductManagement.entity.Product;
import com.jorce.ProductManagement.exception.CategoryNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductService productService;


    public CategoryService(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new CategoryNotFoundException("Category not found for Id: " + categoryId));
    }

    public Category updateCategory(int categoryId, Category category) {
        Category existingCategory = getCategoryById(categoryId);
        existingCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(existingCategory);
    }

    public Product assignCategory(int productId, int categoryId) {

        Product product = productService.getProductById(productId);
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new CategoryNotFoundException("Category not found for categoryId: " + categoryId));

        product.setCategory(category);
        return productService.addProduct(product);
    }

    public String deleteCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new CategoryNotFoundException("Category not found for categoryId: " + categoryId));
        categoryRepository.delete(category);
        return "Deleted category having Id: " + categoryId;
    }

}
