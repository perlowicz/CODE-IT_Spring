package com.example.codeit_db_com.arch.client;

import com.example.codeit_db_com.arch.dto.client.ClientTransactionDTO;
import com.example.codeit_db_com.arch.dto.client.SimpleClientDTO;
import com.example.codeit_db_com.arch.mappers.client.ClientDTOMapper;
import com.example.codeit_db_com.arch.mappers.client.SimpleClientDTOMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Optional<ClientTransactionDTO> getClientById(Long id){
        return clientRepository.findById(id).map(clientDTOMapper::map);
    }

    public Optional<List<SimpleClientDTO>> getAllClients(){
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

    Optional<SimpleClientDTO> replaceClient(Long clientId, SimpleClientDTO simpleClientDTO){
        if (!clientRepository.existsById(clientId))
            return Optional.empty();
        simpleClientDTO.setId(clientId);
        simpleClientDTO.setRegistrationDate(LocalDate.now());
        Client clientToUpdate = simpleClientDTOMapper.map(simpleClientDTO);
        Client updatedEntity = clientRepository.save(clientToUpdate);
        return Optional.of(simpleClientDTOMapper.map(updatedEntity));
    }

    void deleteClient(Long id){
        clientRepository.deleteById(id);
    }
}
