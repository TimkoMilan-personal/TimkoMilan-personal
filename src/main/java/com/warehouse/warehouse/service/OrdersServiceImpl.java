package com.warehouse.warehouse.service;

import com.warehouse.warehouse.model.Orders;
import com.warehouse.warehouse.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrderRepository orderRepository;

    public OrdersServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public List<Orders> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Orders> getOrdered() {
        return orderRepository.findByisOrderedTrue();
    }

    @Override
    public List<Orders> getNotOrdered() {
        return orderRepository.findByisOrderedFalse();
    }

    @Override
    public List<Orders> findByProductId(Long productId) {
        return orderRepository.findByproductId(productId);
    }

    @Override
    public void removeOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public void saveOrder(Orders orders) {
        orderRepository.save(orders);
    }


}
