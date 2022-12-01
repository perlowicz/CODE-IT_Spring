package com.example.codeit_db_com.arch.transaction;

import com.example.codeit_db_com.arch.client.ClientService;
import com.example.codeit_db_com.arch.course.CourseService;
import com.example.codeit_db_com.arch.dto.client.ClientTransactionDTO;
import com.example.codeit_db_com.arch.dto.client.SimpleClientDTO;
import com.example.codeit_db_com.arch.dto.course.SimpleCourseDTO;
import com.example.codeit_db_com.arch.dto.transaction.TransactionDTO;
import com.example.codeit_db_com.arch.dto.transaction.TransactionSaveDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        Optional<List<TransactionDTO>> allTransactionsByUserId = transactionService.getAllTransactionsByUserId(id);
        if (allTransactionsByUserId.isPresent()) {
            SimpleClientDTO client = allTransactionsByUserId.get().get(0).getClient();
            model.addAttribute("client", client);
            model.addAttribute("transactions", allTransactionsByUserId.get());
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
        Optional<List<SimpleClientDTO>> allClients = clientService.getAllClients();
        Optional<List<SimpleCourseDTO>> allCourses = courseService.getAllCourses();
        if (allClients.isPresent() && allCourses.isPresent()){
            model.addAttribute("clients", allClients.get());
            model.addAttribute("courses", allCourses.get());
        }
        return "pages/user-course/alternative";
    }

    @PostMapping("/transactions")
    String saveTransaction(TransactionSaveDTO transactionSaveDTO){
        transactionService.saveTransaction(transactionSaveDTO);
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
