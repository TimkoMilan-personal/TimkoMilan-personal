package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.Orders;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Long> {

    List<Orders> findByisOrderedTrue();
    List<Orders> findByisOrderedFalse();
    List<Orders> findByproductId(Long productId);

}
