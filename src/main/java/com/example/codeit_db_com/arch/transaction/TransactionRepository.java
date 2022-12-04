package com.example.codeit_db_com.arch.transaction;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> getTransactionByClient_Id(Long id);
    List<Transaction> getTransactionByCourse_Id(Long id);
}
