package com.snov.ecommerce.service.impl;

import com.snov.ecommerce.entity.Category;
import com.snov.ecommerce.repository.CategoryRepository;
import com.snov.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public String addCategory(Category category) {
       categoryRepository.addCategory(category.getName(), category.getDescription(), category.getThumbnail());
       return "Done";
    }
}
