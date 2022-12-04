package com.example.codeit_db_com.arch.service;

import com.example.codeit_db_com.arch.repositories.ClientRepository;
import com.example.codeit_db_com.arch.entities.Client;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final Validator validator;

    public ClientService(ClientRepository clientRepository, Validator validator) {
        this.clientRepository = clientRepository;
        this.validator = validator;
    }

    public Optional<Client> getClientById(Long id){
        return clientRepository.findById(id);
    }

    public Optional<List<Client>> getAllClients(){
        List<Client> resultList = new ArrayList<>();
        clientRepository.findAll().forEach(resultList::add);
        return Optional.of(resultList);
    }

    public Client saveClient(Client client){
        Client savedClient = clientRepository.save(client);
        return savedClient;
    }

    public Optional<Client> replaceClient(Client client){
        if (!clientRepository.existsById(client.getId())) {
            return Optional.empty();
        }
        Client updatedEntity = clientRepository.save(client);
        return Optional.of(updatedEntity);
    }

    public Optional<Client> getClientByName(String name){
        return clientRepository.findByUserName(name);
    }

    public Optional<Client> getClientEntityById(Long id){
        return clientRepository.findById(id);
    }
    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }

    public List<String> getAllClientsUsernames(){
        Optional<List<Client>> clients = getAllClients();
        if (clients.isPresent())
            return clients.get().stream().map(Client::getUserName).collect(Collectors.toList());
        else
            return Collections.emptyList();
    }
}
