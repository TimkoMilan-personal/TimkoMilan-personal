package com.warehouse.warehouse.service;

import com.warehouse.warehouse.dto.ProductDto;
import com.warehouse.warehouse.model.Product;

import java.util.List;

public interface ProductService {

    void addNew(ProductDto productDto);

    List<Product> getAll();

    void removeItem(long itemId);

//    List<Product> getByCategory(Long categoryId);
}
