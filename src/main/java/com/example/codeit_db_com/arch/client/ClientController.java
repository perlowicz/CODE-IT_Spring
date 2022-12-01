package com.example.codeit_db_com.arch.client;

import com.example.codeit_db_com.arch.dto.client.ClientTransactionDTO;
import com.example.codeit_db_com.arch.dto.client.SimpleClientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

//@RestController
@Controller
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

//    @GetMapping("/clients/{id}")
//    ResponseEntity<ClientTransactionDTO> getClientById(@PathVariable Long id){
//        return clientService.getClientById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

//    @GetMapping("/clients")
//    ResponseEntity<List<SimpleClientDTO>> getAllClients(){
//        return clientService.getAllClients()
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/clients")
    public String getAllClients(Model model){
        Optional<List<SimpleClientDTO>> allClients = clientService.getAllClients();
        if (allClients.isPresent() && allClients.get().size() > 0){
            model.addAttribute("clients", allClients.orElse(Collections.emptyList()));
        }
        return "pages/user/list";
    }

    @GetMapping("/clients/add") //redirect from list to form
    public String addNewClient(Model model){
        return "pages/user/form";
    }

    @PostMapping("/clients")
    String saveClient(SimpleClientDTO simpleClientDTO){
        clientService.saveClient(simpleClientDTO);
        return "redirect:clients";
    }

//    @PostMapping("/clients")
//    ResponseEntity<SimpleClientDTO> save(@RequestBody SimpleClientDTO simpleClientDTO){
//        SimpleClientDTO savedClient = clientService.saveClient(simpleClientDTO);
//        URI savedClientURI = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(savedClient.getId())
//                .toUri();
//        return ResponseEntity.created(savedClientURI).body(savedClient);
//    }

//    @PutMapping("/clients/{id}")
//    ResponseEntity<?> update(@PathVariable Long id,
//                             @RequestBody SimpleClientDTO simpleClientDTO){
//        return clientService.replaceClient(id, simpleClientDTO)
//                .map(c -> ResponseEntity.noContent().build())
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/clients/{id}")
    String update(@PathVariable Long id, Model model){
        Optional<ClientTransactionDTO> clientById = clientService.getClientById(id);
        if (clientById.isPresent())
            model.addAttribute("client", clientById.get());
        return "pages/user/form-edit";
    }

    @PutMapping("/clients/{id}")
    String updateClient(@PathVariable Long id, SimpleClientDTO simpleClientDTO){
        clientService.replaceClient(id, simpleClientDTO);
        return "redirect:clients";
    }

    @DeleteMapping("/clients/{id}")
    String deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return "pages/user/list";
    }
}
