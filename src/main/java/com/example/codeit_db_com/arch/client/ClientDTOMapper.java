package com.example.codeit_db_com.arch.client;

import org.springframework.stereotype.Service;

@Service
public class ClientDTOMapper {

    public ClientDTO mapClientToDTO(Client client){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setUserName(client.getUserName());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setPassword(client.getPassword());
        clientDTO.setRegistrationDate(client.getRegistrationDate());
        return clientDTO;
    }

    public Client mapSaveDtoToClient(ClientSaveDTO saveDTO){
        Client client = new Client();
        client.setTransactions(saveDTO.getTransactions());
        client.setId(saveDTO.getId());
        client.setEmail(saveDTO.getEmail());
        client.setUserName(saveDTO.getUserName());
        client.setPassword(saveDTO.getPassword());
        client.setRegistrationDate(saveDTO.getRegistrationDate());
        return client;
    }

    public ClientSaveDTO mapClientToSaveDTO(Client client){
        ClientSaveDTO clientDTO = new ClientSaveDTO();
        clientDTO.setId(client.getId());
        clientDTO.setUserName(client.getUserName());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setPassword(client.getPassword());
        clientDTO.setRegistrationDate(client.getRegistrationDate());
        return clientDTO;
    }
}
