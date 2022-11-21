package com.example.codeit_db_com.arch.transaction;

import com.example.codeit_db_com.arch.client.ClientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions/{id}")
    ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id){
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/transactions")
    ResponseEntity<List<TransactionDTO>> getAllClients(){
        return transactionService.getAllTransactions()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
