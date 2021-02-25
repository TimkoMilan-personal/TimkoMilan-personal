package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.dto.SoldProductDto;
import com.warehouse.warehouse.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private ProductService productService;

    public InventoryController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void soldProduct(@RequestBody SoldProductDto soldProduct) {
        productService.soldProduct(soldProduct);
    }


}
