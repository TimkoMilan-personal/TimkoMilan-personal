package com.warehouse.warehouse.service;

import com.warehouse.warehouse.dto.ProductDto;
import com.warehouse.warehouse.model.Category;
import com.warehouse.warehouse.model.Product;
import com.warehouse.warehouse.repository.CategoryRepository;
import com.warehouse.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void addNew(ProductDto productDto) {
        Category category = categoryRepository.getOne(productDto.getCategoryId());
        Product product = new Product();
        product.setCount(productDto.getCount());
        product.setName(productDto.getName());
        product.setNote(productDto.getNote());
        product.setPlace(productDto.getPlace());
        product.setIdCode(productDto.getIdCode());
        product.setCategory(category);
        productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public void removeItem(long itemId) {
        productRepository.deleteById(itemId);
    }

//    @Override
//    public List<Product> getByCategory(Long categoryId) {
//        return productRepository.get;
//    }
}
