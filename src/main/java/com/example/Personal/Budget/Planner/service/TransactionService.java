package com.example.Personal.Budget.Planner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Personal.Budget.Planner.entity.Transaction;
import com.example.Personal.Budget.Planner.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long transaction_id) {
        return transactionRepository.findById(transaction_id);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long transaction_id, Transaction transactionDetails) {
        Transaction transaction = transactionRepository.findById(transaction_id).orElseThrow();
        transaction.setType(transactionDetails.getType());
        transaction.setAmount(transactionDetails.getAmount());
        transaction.setTransaction_date(transactionDetails.getTransaction_date());
        transaction.setDescription(transactionDetails.getDescription());
        transaction.setUser(transactionDetails.getUser());
        transaction.setCategory(transactionDetails.getCategory());
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long transaction_id) {
        transactionRepository.deleteById(transaction_id);
    }
}