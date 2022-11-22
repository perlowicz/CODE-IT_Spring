package com.example.codeit_db_com.arch.transaction;

import com.example.codeit_db_com.arch.dto.transaction.TransactionDTO;
import com.example.codeit_db_com.arch.dto.transaction.TransactionSaveDTO;
import com.example.codeit_db_com.arch.mappers.transaction.TransactionDTOMapper;
import com.example.codeit_db_com.arch.mappers.transaction.TransactionSaveDTOMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionDTOMapper transactionDTOMapper;
    private final TransactionSaveDTOMapper transactionSaveDTOMapper;

    public TransactionService(TransactionRepository transactionRepository,
                               TransactionDTOMapper transactionDTOMapper,
                               TransactionSaveDTOMapper transactionSaveDTOMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionDTOMapper = transactionDTOMapper;
        this.transactionSaveDTOMapper = transactionSaveDTOMapper;
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

    TransactionSaveDTO saveTransaction(TransactionSaveDTO transactionSaveDTO){
        if (transactionSaveDTO.getOpinion() == null) //sytuacja gdy użytkownik nie dodał opinii
            transactionSaveDTO.setOpinion("brak opinii");
        transactionSaveDTO.setSignupDate(LocalDate.now());
        transactionSaveDTO.setExpirationDate(LocalDate.now().plusYears(1));
        Transaction map = transactionSaveDTOMapper.map(transactionSaveDTO);
        Transaction savedTransaction = transactionRepository.save(map);
        return transactionSaveDTOMapper.map(savedTransaction);
    }
}
