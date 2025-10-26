package com.example.Personal.Budget.Planner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Personal.Budget.Planner.entity.Category;
import com.example.Personal.Budget.Planner.entity.User;
import com.example.Personal.Budget.Planner.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserService userService;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long category_id) {
        return categoryRepository.findById(category_id);
    }

    public Category createCategory(Long user_id, Category categoryDetails) {
        User user = userService.getUserById(user_id).orElseThrow();

        Category category = new Category();
        category.setUser(user);
        category.setName(categoryDetails.getName());
        category.setBudget_limit(categoryDetails.getBudget_limit());

        return categoryRepository.save(category);
    }

    public Category updateCategory(Long category_id, Long user_id, Category categoryDetails) {
        User user = userService.getUserById(user_id).orElseThrow();
        Category category = categoryRepository.findById(category_id).orElseThrow();
        category.setUser(user);
        category.setName(categoryDetails.getName());
        category.setBudget_limit(categoryDetails.getBudget_limit());

        return categoryRepository.save(category);
    }

    public void deleteCategory(Long category_id) {
        categoryRepository.deleteById(category_id);
    }
}