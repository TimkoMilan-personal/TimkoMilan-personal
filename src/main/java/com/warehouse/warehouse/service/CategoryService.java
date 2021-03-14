package com.warehouse.warehouse.service;

import com.warehouse.warehouse.dto.CategoryCreateDto;
import com.warehouse.warehouse.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category addNew(CategoryCreateDto categoryCreateDto);

    void removeById(Long categoryId);

    Category findById(Long categoryId);

}
