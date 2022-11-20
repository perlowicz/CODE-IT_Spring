package com.example.codeit_db_com.arch.client;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientDTOMapper clientDTOMapper;

    public ClientService(ClientRepository clientRepository, ClientDTOMapper clientDTOMapper) {
        this.clientRepository = clientRepository;
        this.clientDTOMapper = clientDTOMapper;
    }

    Optional<ClientDTO> getClientById(Long id){
        return clientRepository.findById(id).map(clientDTOMapper::map);
    }
}
