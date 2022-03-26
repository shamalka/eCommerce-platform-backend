package com.snov.ecommerce.controller;

import com.snov.ecommerce.entity.Category;
import com.snov.ecommerce.entity.Product;
import com.snov.ecommerce.service.CategoryService;
import com.snov.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public String addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }
}
