package com.example.codeit_db_com.arch.transaction;

import com.example.codeit_db_com.arch.client.ClientDTO;
import com.example.codeit_db_com.arch.dto.SimpleClientDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        transactionRepository.findAll().forEach(transaction -> {
            TransactionDTO transactionDTO = transactionDTOMapper.map(transaction);
            resultList.add(transactionDTO);
        });
        return Optional.of(resultList);
    }

    TransactionDTO saveTransaction(TransactionDTO transactionSaveDTO){
        Transaction map = transactionDTOMapper.map(transactionSaveDTO);
        map.setSignupDate(LocalDate.now());
        map.setExpirationDate(LocalDate.now().plusYears(1));
        Transaction savedTransaction = transactionRepository.save(map);
        return transactionDTOMapper.map(savedTransaction);
    }
}
