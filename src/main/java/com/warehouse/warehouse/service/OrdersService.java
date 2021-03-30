package com.warehouse.warehouse.service;

import com.warehouse.warehouse.model.Orders;

import java.util.List;

public interface OrdersService {
    /**
     * method return all Orders set in the database
     * return List<Orders>
     */
    List<Orders> getAll();
    /**
     * method return all Orders that has attribute isOrdered set to TRUE
     * return List<Orders>
     */
    List<Orders> getOrdered();
    /**
     * method return all Orders that has attribute isOrdered set to FALSE
     * return List<Orders>
     */
    List<Orders> getNotOrdered();
    /**
     * method return all Orders that include specified ProductId to sort Orders by product.
     * return List<Orders>
     */
    List<Orders> findByProductId(Long orderId);
    /**
     * method remove Order by Id corresponding to id stored in DB
     */
    void removeOrderById(Long orderId);
    /**
     * method to save new Order record to Database. Including productId and isOrdered set to FALSE
     */
    void saveOrder(Orders orders);
}
