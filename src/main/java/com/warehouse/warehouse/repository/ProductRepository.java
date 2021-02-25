package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    List<Product>findProductsByCategoryId(Long categoryId);
}
