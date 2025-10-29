package com.example.Personal.Budget.Planner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Personal.Budget.Planner.entity.Transaction;
import com.example.Personal.Budget.Planner.entity.User;
import com.example.Personal.Budget.Planner.entity.Category;
import com.example.Personal.Budget.Planner.service.TransactionService;
import com.example.Personal.Budget.Planner.service.UserService;
import com.example.Personal.Budget.Planner.service.CategoryService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getAllTransactions(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "transactions/list";
    }

    @GetMapping("/new")
    public String createTransactionForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "transactions/form";
    }

    @PostMapping
    public String createTransaction(@ModelAttribute Transaction transaction) {
        if (transaction.getTransaction_date() == null) {
            transaction.setTransaction_date(LocalDateTime.now());
        }
        Long user_id = transaction.getUser().getUser_id();
        Long category_id = transaction.getCategory().getCategory_id();

        transaction.setUser(userService.getUserById(user_id).orElseThrow());
        transaction.setCategory(categoryService.getCategoryById(category_id).orElseThrow());

        transactionService.createTransaction(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/edit/{transaction_id}")
    public String editTransactionForm(@PathVariable Long transaction_id, Model model) {
        model.addAttribute("transaction", transactionService.getTransactionById(transaction_id).orElseThrow());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "transactions/form";
    }

    @PostMapping("/update/{transaction_id}")
    public String updateTransaction(@PathVariable Long transaction_id, @ModelAttribute Transaction transaction) {
        transactionService.updateTransaction(transaction_id, transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/delete/{transaction_id}")
    public String deleteTransaction(@PathVariable Long transaction_id) {
        transactionService.deleteTransaction(transaction_id);
        return "redirect:/transactions";
    }
}