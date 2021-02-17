package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.dto.ProductDto;
import com.warehouse.warehouse.model.Product;
import com.warehouse.warehouse.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productSrvice;

    public ProductController(ProductService productSrvice) {
        this.productSrvice = productSrvice;
    }

    @GetMapping
    public List<Product> getAll() {
        return productSrvice.getAll();
    }

//    @GetMapping
//    public List<Product> getByCategory(@RequestParam("categoryId")Long categoryId){
//        return productSrvice.getByCategory(categoryId);
//    }

    @PostMapping
    public void addNew(@RequestBody ProductDto productDto) {
        productSrvice.addNew(productDto);
    }

    @DeleteMapping
    public void removeItem(@RequestParam("itemId") Long itemId) {
        productSrvice.removeItem(itemId);
    }

}
