package com.example.codeit_db_com.arch.transaction;

import com.example.codeit_db_com.arch.client.ClientDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionDTOMapper transactionDTOMapper;

    public TransactionService(TransactionRepository transactionRepository,
                              TransactionDTOMapper transactionDTOMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionDTOMapper = transactionDTOMapper;
    }

    Optional<TransactionDTO> getTransactionById(Long id){
        return transactionRepository.findById(id).map(transactionDTOMapper::map);
    }

    Optional<List<TransactionDTO>> getAllTransactions(){
        List<TransactionDTO> resultList = new ArrayList<>();
        transactionRepository.findAll().iterator().forEachRemaining(transaction -> {
            TransactionDTO mappedTransaction = transactionDTOMapper.map(transaction);
            resultList.add(mappedTransaction);
        });
        return Optional.of(resultList);
    }
}
