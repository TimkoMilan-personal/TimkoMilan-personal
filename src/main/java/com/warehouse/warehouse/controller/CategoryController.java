package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.dto.CategoryCreateDto;
import com.warehouse.warehouse.model.Category;
import com.warehouse.warehouse.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @PostMapping
    public void addNew(@RequestBody CategoryCreateDto categoryCreateDto) {
        categoryService.addNew(categoryCreateDto);
    }

    @DeleteMapping
    public void removeByCategoryId(@RequestParam("categoryId")Long categoryId){
        categoryService.removeById(categoryId);
    }



}
