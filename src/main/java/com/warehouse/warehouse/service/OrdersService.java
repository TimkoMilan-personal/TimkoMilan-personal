package com.warehouse.warehouse.service;

import com.warehouse.warehouse.model.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> getAll();

    List<Orders> getOrdered();

    List<Orders> getNotOrdered();

    List<Orders> findByProductId(Long orderId);

    void removeOrderById(Long orderId);

    void saveOrder(Orders orders);
}
