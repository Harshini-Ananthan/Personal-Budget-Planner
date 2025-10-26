package com.example.Personal.Budget.Planner.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private BigDecimal budget_limit;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
