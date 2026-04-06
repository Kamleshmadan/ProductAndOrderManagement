package com.jorce.ProductManagement.Repository;

import com.jorce.ProductManagement.entity.Category;
import com.jorce.ProductManagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByProductName(String productName);

    Optional<List<Product>> findByCategory(Category category);
}
