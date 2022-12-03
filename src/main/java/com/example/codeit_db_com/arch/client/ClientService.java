package com.example.codeit_db_com.arch.client;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Client> getClientById(Long id){
        return clientRepository.findById(id);
    }

    public Optional<List<Client>> getAllClients(){
        List<Client> resultList = new ArrayList<>();
        clientRepository.findAll().forEach(resultList::add);
        return Optional.of(resultList);
    }

    Client saveClient(Client client){
        Client savedClient = clientRepository.save(client);
        return savedClient;
    }

    Optional<Client> replaceClient(Client client){
        if (!clientRepository.existsById(client.getId()))
            return Optional.empty();
//        client.setId(clientId);
//        client.setRegistrationDate(LocalDate.now());
        Client updatedEntity = clientRepository.save(client);
        return Optional.of(updatedEntity);
    }

    public Optional<Client> getClientEntityById(Long id){
        return clientRepository.findById(id);
    }
    void deleteClient(Long id){
        clientRepository.deleteById(id);
    }
}
