package com.example.codeit_db_com.arch.mappers.client;

import com.example.codeit_db_com.arch.client.Client;
import com.example.codeit_db_com.arch.dto.client.ClientTransactionDTO;
import com.example.codeit_db_com.arch.dto.course.SimpleCourseDTO;
import com.example.codeit_db_com.arch.dto.client.TransactionClientDTO;
import com.example.codeit_db_com.arch.transaction.Transaction;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientDTOMapper {

    public ClientTransactionDTO map(Client client){
        ClientTransactionDTO clientTransactionDTO = new ClientTransactionDTO();

        clientTransactionDTO.setId(client.getId());
        clientTransactionDTO.setEmail(client.getEmail());
        clientTransactionDTO.setPassword(client.getPassword());
        clientTransactionDTO.setUserName(client.getUserName());
        clientTransactionDTO.setRegistrationDate(client.getRegistrationDate());

        clientTransactionDTO.setTransactions(mapTransactionsForClient(client.getTransactions()));

        return clientTransactionDTO;
    }

    private static List<TransactionClientDTO> mapTransactionsForClient(List<Transaction> transactions){
        return transactions.stream().map(
                transaction -> {
                    TransactionClientDTO transactionClientDTO = new TransactionClientDTO();
                    SimpleCourseDTO simpleCourseDTO = new SimpleCourseDTO();

                    simpleCourseDTO.setId(transaction.getCourse().getId());
                    simpleCourseDTO.setName(transaction.getCourse().getName());
                    simpleCourseDTO.setDescription(transaction.getCourse().getDescription());
                    simpleCourseDTO.setPrice(transaction.getCourse().getPrice());

                    transactionClientDTO.setId(transaction.getId());
                    transactionClientDTO.setCourse(simpleCourseDTO);
                    transactionClientDTO.setSignupDate(transaction.getSignupDate());
                    transactionClientDTO.setExpirationDate(transaction.getExpirationDate());
                    transactionClientDTO.setOpinion(transaction.getOpinion());

                    return transactionClientDTO;
                }
        ).collect(Collectors.toList());
    }
}
