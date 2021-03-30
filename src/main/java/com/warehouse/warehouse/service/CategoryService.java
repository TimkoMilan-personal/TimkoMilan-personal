package com.warehouse.warehouse.service;

import com.warehouse.warehouse.dto.CategoryCreateDto;
import com.warehouse.warehouse.model.Category;

import java.util.List;

public interface CategoryService {
    /**
     * method to return all Category set in the database
     * return List<Category>
     */
    List<Category> getAll();
    /**
     * method to save new created Category, data stored in CategoryCreateDto object from request
     * return Category object with auto-generated id.
     */
    Category addNew(CategoryCreateDto categoryCreateDto);
    /**
     * method to remove Category from database by id
     */
    void removeById(Long categoryId);
    /**
     * method to return exactly Category by id stored in DB
     * return Category object
     */
    Category findById(Long categoryId);

}
