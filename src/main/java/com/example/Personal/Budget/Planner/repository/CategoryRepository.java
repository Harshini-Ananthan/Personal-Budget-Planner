package com.example.Personal.Budget.Planner.repository;

import com.example.Personal.Budget.Planner.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<User, Long> {
}
