package com.example.codeit_db_com.arch.client;

import com.example.codeit_db_com.arch.dto.SimpleCourseDTO;
import com.example.codeit_db_com.arch.dto.ClientTransactionDTO;
import com.example.codeit_db_com.arch.dto.SimpleClientDTO;
import com.example.codeit_db_com.arch.transaction.Transaction;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientDTOMapper {

    ClientDTO map(Client client){
        ClientDTO clientDTO = new ClientDTO();
        List<ClientTransactionDTO> mappedTransactions = mapTransactions(client.getTransactions());

        clientDTO.setTransactions(mappedTransactions);
        clientDTO.setId(client.getId());
        clientDTO.setPassword(client.getPassword());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setRegistrationDate(client.getRegistrationDate());
        clientDTO.setUserName(client.getUserName());

        return clientDTO;
    }

    private static List<ClientTransactionDTO> mapTransactions(List<Transaction> transactions){
        return transactions.stream().map(
                transaction -> {
                    SimpleCourseDTO simpleCourseDTO = new SimpleCourseDTO();
                    ClientTransactionDTO clientTransactionDTO = new ClientTransactionDTO();

                    simpleCourseDTO.setId(transaction.getCourse().getId());
                    simpleCourseDTO.setName(transaction.getCourse().getName());
                    simpleCourseDTO.setDescription(transaction.getCourse().getDescription());
                    simpleCourseDTO.setPrice(transaction.getCourse().getPrice());

                    clientTransactionDTO.setId(transaction.getId());
                    clientTransactionDTO.setCourse(simpleCourseDTO);
                    clientTransactionDTO.setSignupDate(transaction.getSignupDate());
                    clientTransactionDTO.setExpirationDate(transaction.getExpirationDate());
                    clientTransactionDTO.setOpinion(transaction.getOpinion());
                    return clientTransactionDTO;
                }
        ).collect(Collectors.toList());
    }
}
