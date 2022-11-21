package com.example.codeit_db_com.arch.client;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        return clientRepository.findById(id).map(clientDTOMapper::mapClientToDTO);
    }

    Optional<List<ClientDTO>> getAllClients(){
        List<ClientDTO> resultList = new ArrayList<>();
        clientRepository.findAll().iterator().forEachRemaining(client -> {
            ClientDTO mappedClient = clientDTOMapper.mapClientToDTO(client);
            resultList.add(mappedClient);
        });
        return Optional.of(resultList);
    }

    ClientSaveDTO saveClient(ClientSaveDTO clientSaveDTO){
        Client mappedClient = clientDTOMapper.mapSaveDtoToClient(clientSaveDTO);
        Client savedClient = clientRepository.save(mappedClient);
        return clientDTOMapper.mapClientToSaveDTO(savedClient);
    }
}
