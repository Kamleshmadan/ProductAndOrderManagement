package com.jorce.ProductManagement.Repository;

import com.jorce.ProductManagement.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {


}
