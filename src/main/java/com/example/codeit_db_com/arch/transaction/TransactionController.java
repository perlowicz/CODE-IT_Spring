package com.example.codeit_db_com.arch.transaction;

import com.example.codeit_db_com.arch.client.Client;
import com.example.codeit_db_com.arch.client.ClientService;
import com.example.codeit_db_com.arch.course.Course;
import com.example.codeit_db_com.arch.course.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

//@RestController
@Controller
public class TransactionController {

    private final TransactionService transactionService;
    private final ClientService clientService;
    private final CourseService courseService;

    public TransactionController(TransactionService transactionService,
                                  ClientService clientService,
                                  CourseService courseService) {
        this.transactionService = transactionService;
        this.clientService = clientService;
        this.courseService = courseService;
    }

    @GetMapping("/transactions/{id}")
    String getTransactionById(@PathVariable Long id, Model model){
        Optional<List<Transaction>> allTransactionsByUserId = transactionService.getAllTransactionsByUserId(id);
        if (allTransactionsByUserId.isPresent() && allTransactionsByUserId.get().size() > 0) {
            Optional<Client> clientEntityById = clientService.getClientEntityById(id);
            model.addAttribute("client", clientEntityById.get());
            model.addAttribute("transactions", allTransactionsByUserId.get());
        } else {
            Optional<Client> clientEntityById = clientService.getClientEntityById(id);
            model.addAttribute("client", clientEntityById.get());
        }
        return "pages/user-course/details";
    }

//    @GetMapping("/transactions")
//    ResponseEntity<List<TransactionDTO>> getAllTransactions(){
//        return transactionService.getAllTransactions()
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/transactions")
    String getAllTransactions(Model model){
        Optional<List<Client>> allClients = clientService.getAllClients();
        Optional<List<Course>> allCourses = courseService.getAllCourses();
        if (allClients.isPresent() && allCourses.isPresent()){
            model.addAttribute("clients", allClients.get());
            model.addAttribute("courses", allCourses.get());
        }
        return "pages/user-course/alternative";
    }

    @PostMapping("/transactions")
    String saveTransaction(@ModelAttribute("clients") Client client){
        return "redirect:transactions";
    }

//    @PostMapping("/transactions")
//    ResponseEntity<TransactionSaveDTO> saveTransaction(@RequestBody TransactionSaveDTO transactionSaveDTO){
//        TransactionSaveDTO saved = transactionService.saveTransaction(transactionSaveDTO);
//        URI savedClientURI = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(saved.getId())
//                .toUri();
//        return ResponseEntity.created(savedClientURI).body(saved);
//    }
}
