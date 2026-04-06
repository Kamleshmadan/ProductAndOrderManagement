package com.jorce.ProductManagement.controller;

import com.jorce.ProductManagement.entity.Category;
import com.jorce.ProductManagement.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(category));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/updateCategory/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable int categoryId, @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, category));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable int categoryId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.deleteCategory(categoryId));
    }

}

