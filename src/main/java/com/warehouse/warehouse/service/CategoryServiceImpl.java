package com.warehouse.warehouse.service;

import com.warehouse.warehouse.dto.CategoryCreateDto;
import com.warehouse.warehouse.model.Category;
import com.warehouse.warehouse.model.Product;
import com.warehouse.warehouse.repository.CategoryRepository;
import com.warehouse.warehouse.repository.ProductRepository;
import com.warehouse.warehouse.util.ProductUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductService productService;
    private final ProductUtil productUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, ProductService productService, ProductUtil productUtil) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
        this.productUtil = productUtil;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addNew(CategoryCreateDto categoryCreateDto) {
        Category category = new Category();
        category.setName(categoryCreateDto.getName());
        return categoryRepository.save(category);
    }

    @Override
    public void removeById(Long categoryId) {
        if (categoryId!=1L){
            List<Product> products =  productService.getByCategory(categoryId);
            Optional<Category> defaultCategory = categoryRepository.findById(1L);
            for (Product product : products) {
                product.setCategory(defaultCategory.get());
            }
            categoryRepository.deleteById(categoryId);
        }//Status code cannot remove defaultCategory
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryRepository.getOne(categoryId);
    }
}
