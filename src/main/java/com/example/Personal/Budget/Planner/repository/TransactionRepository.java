package com.example.Personal.Budget.Planner.repository;

import com.example.Personal.Budget.Planner.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
