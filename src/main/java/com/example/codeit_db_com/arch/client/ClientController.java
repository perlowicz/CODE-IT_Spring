package com.example.codeit_db_com.arch.client;

import com.example.codeit_db_com.arch.dto.client.ClientTransactionDTO;
import com.example.codeit_db_com.arch.dto.client.SimpleClientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients/{id}")
    ResponseEntity<ClientTransactionDTO> getClientById(@PathVariable Long id){
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/clients")
    ResponseEntity<List<SimpleClientDTO>> getAllClients(){
        return clientService.getAllClients()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/clients")
    ResponseEntity<SimpleClientDTO> save(@RequestBody SimpleClientDTO simpleClientDTO){
        SimpleClientDTO savedClient = clientService.saveClient(simpleClientDTO);
        URI savedClientURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedClient.getId())
                .toUri();
        return ResponseEntity.created(savedClientURI).body(savedClient);
    }

    @PutMapping("/clients/{id}")
    ResponseEntity<?> update(@PathVariable Long id,
                                           @RequestBody SimpleClientDTO simpleClientDTO){
        return clientService.replaceClient(id, simpleClientDTO)
                .map(c -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/clients/{id}")
    ResponseEntity<?> deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
