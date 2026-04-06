package com.jorce.ProductManagement.Repository;

import com.jorce.ProductManagement.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Integer> {
    Optional<CartItems> findByCartIdAndProductId(int id, int productId);

    List<CartItems> findByCartId(int cartId);
}
