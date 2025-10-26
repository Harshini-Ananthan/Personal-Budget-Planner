package com.example.Personal.Budget.Planner.repository;

import com.example.Personal.Budget.Planner.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
