package com.warehouse.warehouse.service;

import com.warehouse.warehouse.dto.ProductCreateDto;
import com.warehouse.warehouse.dto.SoldProductDto;
import com.warehouse.warehouse.model.Product;

import java.util.List;

public interface ProductService {

    Product addNew(ProductCreateDto productCreateDto);

    Product addAmount(Long productId, int amount);

    List<Product> getAll();

    void removeItem(long itemId);

    List<Product> getByCategory(Long categoryId);

    Product updateProduct(Long productId, ProductCreateDto productCreateDto);

    void soldProduct(SoldProductDto soldProduct);

    Product getById(Long productId);
}
