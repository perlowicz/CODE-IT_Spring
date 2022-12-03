package com.example.codeit_db_com.arch.transaction;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    Optional<Transaction> getTransactionById(Long id){
        return transactionRepository.findById(id);
    }

    Optional<List<Transaction>> getAllTransactions(){
        List<Transaction> resultList = new ArrayList<>();
        transactionRepository.findAll().forEach(resultList::add);
        return Optional.of(resultList);
    }

    Optional<List<Transaction>> getAllTransactionsByUserId(Long id){
        List<Transaction> resultList = new ArrayList<>();
        transactionRepository.getTransactionByClient_Id(id).forEach(resultList::add);
        return Optional.of(resultList);
    }

    Transaction saveTransaction(Transaction transaction){
        if (transaction.getOpinion() == null) //sytuacja gdy użytkownik nie dodał opinii
            transaction.setOpinion("brak opinii");
        Transaction savedTransaction = transactionRepository.save(transaction);
        return savedTransaction;
    }
}
