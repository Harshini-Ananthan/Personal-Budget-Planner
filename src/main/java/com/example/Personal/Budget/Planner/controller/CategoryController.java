package com.example.Personal.Budget.Planner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Personal.Budget.Planner.entity.Category;
import com.example.Personal.Budget.Planner.service.CategoryService;
import com.example.Personal.Budget.Planner.entity.User;
import com.example.Personal.Budget.Planner.service.UserService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories/list";
    }

    @GetMapping("/new")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("users", userService.getAllUsers());
        return "categories/form";
    }

    @PostMapping
    public String createCategory(@RequestParam("user_id") Long user_id, @ModelAttribute Category category) {
        categoryService.createCategory(user_id, category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{category_id}")
    public String editCategoryForm(@PathVariable Long category_id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(category_id).orElseThrow());
        model.addAttribute("users", userService.getAllUsers());
        return "categories/form";
    }

    @PostMapping("/update/{category_id}")
    public String updateCategory(@PathVariable Long category_id, @RequestParam("user_id") Long user_id,
            @ModelAttribute Category category) {
        categoryService.updateCategory(category_id, user_id, category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{category_id}")
    public String deleteCategory(@PathVariable Long category_id) {
        categoryService.deleteCategory(category_id);
        return "redirect:/categories";
    }
}