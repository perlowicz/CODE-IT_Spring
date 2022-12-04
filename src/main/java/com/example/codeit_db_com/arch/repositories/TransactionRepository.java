package com.example.codeit_db_com.arch.repositories;

import com.example.codeit_db_com.arch.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> getTransactionByClient_Id(Long id);
    List<Transaction> getTransactionByCourse_Id(Long id);
}
