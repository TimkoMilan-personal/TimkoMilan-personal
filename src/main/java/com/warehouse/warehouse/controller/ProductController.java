package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.dto.ProductCreateDto;
import com.warehouse.warehouse.model.Product;
import com.warehouse.warehouse.service.ProductService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<List<Product>> getByCategory(@RequestParam("categoryId") Long categoryId) {
        List<Product> byCategory = productService.getByCategory(categoryId);
        return ResponseEntity.ok(byCategory);
    }

    @PostMapping
    public ResponseEntity<Product> addNew(@RequestBody ProductCreateDto productCreateDto) {
        Product product = productService.addNew(productCreateDto);
        return ResponseEntity.ok(product);

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
