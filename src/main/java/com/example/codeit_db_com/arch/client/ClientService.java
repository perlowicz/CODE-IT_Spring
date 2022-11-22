package com.example.codeit_db_com.arch.client;

import com.example.codeit_db_com.arch.dto.SimpleClientDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientDTOMapper clientDTOMapper;
    private final SimpleClientDTOMapper simpleClientDTOMapper;

    public ClientService(ClientRepository clientRepository, ClientDTOMapper clientDTOMapper, SimpleClientDTOMapper simpleClientDTOMapper) {
        this.clientRepository = clientRepository;
        this.clientDTOMapper = clientDTOMapper;
        this.simpleClientDTOMapper = simpleClientDTOMapper;
    }

    Optional<ClientDTO> getClientById(Long id){
        return clientRepository.findById(id).map(clientDTOMapper::map);
    }

    Optional<List<SimpleClientDTO>> getAllClients(){
        List<SimpleClientDTO> resultList = new ArrayList<>();
        clientRepository.findAll().forEach(client -> {
            SimpleClientDTO simpleClientDTO = simpleClientDTOMapper.map(client);
            resultList.add(simpleClientDTO);
        });
        return Optional.of(resultList);
    }

    SimpleClientDTO saveClient(SimpleClientDTO simpleClientDTO){
        Client map = simpleClientDTOMapper.map(simpleClientDTO);
        Client savedClient = clientRepository.save(map);
        return simpleClientDTOMapper.map(savedClient);
    }
}
