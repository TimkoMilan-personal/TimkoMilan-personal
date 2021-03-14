package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.model.Orders;
import com.warehouse.warehouse.model.Product;
import com.warehouse.warehouse.service.OrdersService;
import com.warehouse.warehouse.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;
    private final ProductService productService;

    public OrdersController(OrdersService ordersService, ProductService productService) {
        this.ordersService = ordersService;
        this.productService = productService;
    }

    @GetMapping
    public List<Orders> getAllOrders() {
        return ordersService.getAll();
    }

    @GetMapping("/active")
    public List<Orders> getOrdered() {
        return ordersService.getOrdered();
    }

    @GetMapping("/inactive")
    public List<Orders> getNotOrdered() {
        return ordersService.getNotOrdered();
    }

    @PostMapping("/receive")
    public Product receiveProduct(@RequestParam("productId") Long productId, @RequestParam("amount") int amount) {
        return productService.addAmount(productId, amount);

    }


}
