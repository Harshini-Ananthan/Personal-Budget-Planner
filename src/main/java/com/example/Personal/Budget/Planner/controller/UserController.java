package com.example.Personal.Budget.Planner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Personal.Budget.Planner.entity.User;
import com.example.Personal.Budget.Planner.service.UserService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/list";
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users/form";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user) {
        if (user.getJoin_date() == null) {
            user.setJoin_date(LocalDateTime.now());
        }
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{user_id}")
    public String editUserForm(@PathVariable Long user_id, Model model) {
        model.addAttribute("user", userService.getUserById(user_id).orElseThrow());
        return "users/form";
    }

    @PostMapping("/update/{user_id}")
    public String updateUser(@PathVariable Long user_id, @ModelAttribute User user) {
        userService.updateUser(user_id, user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{user_id}")
    public String deleteUser(@PathVariable Long user_id) {
        userService.deleteUser(user_id);
        return "redirect:/users";
    }
}