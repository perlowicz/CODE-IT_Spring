package com.example.codeit_db_com.arch.controllers;

import com.example.codeit_db_com.arch.entities.Client;
import com.example.codeit_db_com.arch.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditor;
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

    @GetMapping("/clients")
    public String getAllClients(Model model){
        Optional<List<Client>> allClients = clientService.getAllClients();
        if (allClients.isPresent() && allClients.get().size() > 0){
            model.addAttribute("clients", allClients.orElse(Collections.emptyList()));
        }
        return "pages/user/list";
    }

    @GetMapping("/clients/add") //redirect from list to form
    public String addNewClient(Model model){
        model.addAttribute("client", new Client());
        return "pages/user/form";
    }

    @GetMapping("/clients/edit/{id}") //redirect from list to form-edit
    String update(@PathVariable Long id, Model model){
        Optional<Client> clientById = clientService.getClientById(id);
        if (clientById.isPresent())
            model.addAttribute("client", clientById.get());
        return "pages/user/form-edit";
    }

    @PostMapping("/clients") //adding new client
    String saveClient(@Valid @ModelAttribute("client") Client client, BindingResult bindingResult){
        if (!clientService.validEmail(client.getEmail())){
            bindingResult.reject("errorCode1", "errorCode2");
            bindingResult.reject("errorCode2", "errorCode1");
        }
        if (bindingResult.hasErrors()){
            return "pages/user/form";
        } else {
            clientService.saveClient(client);
            return "redirect:/clients";
        }
    }

    @PostMapping("/clients/edit/{id}") //replacing client
    String updateClient(@Valid @ModelAttribute("client") Client client, BindingResult bindingResult){
        if (!clientService.validEmail(client.getEmail())){
            bindingResult.reject("errorCode1", "errorCode2");
            bindingResult.reject("errorCode2", "errorCode1");
        }
        if (bindingResult.hasErrors()){
            return "pages/user/form-edit";
        } else {
            clientService.replaceClient(client);
            return "redirect:/clients";
        }
    }

    @GetMapping("/clients/delete/{id}")
    String deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return "redirect:/clients";
    }
}
