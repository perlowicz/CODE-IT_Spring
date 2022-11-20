package com.example.codeit_db_com.arch.client;

import org.springframework.stereotype.Service;

@Service
public class ClientDTOMapper {

    public ClientDTO map(Client client){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setUserName(client.getUserName());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setPassword(client.getPassword());
        clientDTO.setRegistrationDate(client.getRegistrationDate());
        return clientDTO;
    }
}
