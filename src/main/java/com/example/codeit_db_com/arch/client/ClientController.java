package com.example.codeit_db_com.arch.client;

import com.example.codeit_db_com.arch.course.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients/{id}")
    ResponseEntity<ClientDTO> getClientById(@PathVariable Long id){
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/clients")
    ResponseEntity<List<ClientDTO>> getAllClients(){
        return clientService.getAllClients()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/clients")
    ResponseEntity<ClientSaveDTO> saveClient(@RequestBody ClientSaveDTO clientSaveDTO){
        ClientSaveDTO savedClient = clientService.saveClient(clientSaveDTO);
        URI savedClientUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedClient.getId())
                .toUri();
        return ResponseEntity.created(savedClientUri).body(savedClient);
    }
}
