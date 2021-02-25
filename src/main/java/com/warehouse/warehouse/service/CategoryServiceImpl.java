package com.warehouse.warehouse.service;

import com.warehouse.warehouse.dto.CategoryCreateDto;
import com.warehouse.warehouse.model.Category;
import com.warehouse.warehouse.model.Product;
import com.warehouse.warehouse.repository.CategoryRepository;
import com.warehouse.warehouse.repository.ProductRepository;
import com.warehouse.warehouse.util.ProductUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductUtil productUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, ProductUtil productUtil) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productUtil = productUtil;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void addNew(CategoryCreateDto categoryCreateDto) {
        Category category = new Category();
        category.setName(categoryCreateDto.getName());
        categoryRepository.save(category);
    }

    @Override
    public void removeById(Long categoryId) {
        if (categoryId!=1L){
            List<Product> products =  productRepository.findProductsByCategoryId(categoryId);
            Optional<Category> defaultCategory = categoryRepository.findById(1L);
            for (Product product : products) {
                product.setCategory(defaultCategory.get());
            }
            categoryRepository.deleteById(categoryId);
        }//Status code cannot remove defaultCategory
    }
}
