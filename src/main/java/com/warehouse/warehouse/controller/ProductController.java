package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.dto.ProductCreateDto;
import com.warehouse.warehouse.model.Product;
import com.warehouse.warehouse.service.ProductService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/category")
    public List<Product> getByCategory(@RequestParam("categoryId") Long categoryId) {
        return productService.getByCategory(categoryId);
    }

    @PostMapping
    public void addNew(@RequestBody ProductCreateDto productCreateDto) {
        productService.addNew(productCreateDto);
    }

    @DeleteMapping
    public void removeItem(@RequestParam("itemId") Long itemId) {
        productService.removeItem(itemId);
    }

    @PutMapping
    public Product updateItem(@RequestBody ProductCreateDto productCreateDto, @RequestParam("productId") Long productId) {
        return productService.updateProduct(productId, productCreateDto);
    }
}
