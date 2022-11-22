package com.example.codeit_db_com.arch.transaction;

import com.example.codeit_db_com.arch.dto.transaction.TransactionDTO;
import com.example.codeit_db_com.arch.dto.transaction.TransactionSaveDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    ResponseEntity<List<TransactionDTO>> getAllTransactions(){
        return transactionService.getAllTransactions()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/transactions")
    ResponseEntity<TransactionSaveDTO> saveTransaction(@RequestBody TransactionSaveDTO transactionSaveDTO){
        TransactionSaveDTO saved = transactionService.saveTransaction(transactionSaveDTO);
        URI savedClientURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(savedClientURI).body(saved);
    }
}
