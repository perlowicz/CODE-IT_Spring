package com.example.codeit_db_com.arch.client;

import com.example.codeit_db_com.arch.dto.SimpleClientDTO;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SimpleClientDTOMapper {

    public SimpleClientDTO map(Client client){
        SimpleClientDTO simpleClientDTO = new SimpleClientDTO();

        simpleClientDTO.setId(client.getId());
        simpleClientDTO.setUserName(client.getUserName());
        simpleClientDTO.setEmail(client.getEmail());
        simpleClientDTO.setPassword(client.getPassword());
        simpleClientDTO.setRegistrationDate(client.getRegistrationDate());

        return simpleClientDTO;
    }

    public Client map(SimpleClientDTO simpleClientDTO){
        Client client = new Client();
        client.setId(simpleClientDTO.getId());
        client.setUserName(simpleClientDTO.getUserName());
        client.setEmail(simpleClientDTO.getEmail());
        client.setPassword(simpleClientDTO.getPassword());
        client.setRegistrationDate(simpleClientDTO.getRegistrationDate());
        client.setTransactions(Collections.emptyList());
        return client;
    }
}
