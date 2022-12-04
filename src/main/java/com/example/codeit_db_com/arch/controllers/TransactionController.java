package com.example.codeit_db_com.arch.controllers;

import com.example.codeit_db_com.arch.entities.Client;
import com.example.codeit_db_com.arch.service.ClientService;
import com.example.codeit_db_com.arch.entities.Course;
import com.example.codeit_db_com.arch.service.CourseService;
import com.example.codeit_db_com.arch.dto.TransactionSaveDTO;
import com.example.codeit_db_com.arch.entities.Transaction;
import com.example.codeit_db_com.arch.service.TransactionService;
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
    public String getTransactionById(@PathVariable Long id, Model model){
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

    @GetMapping("/transactions/courses/{id}")
    public String getCoursesInfo(@PathVariable Long id, Model model){
        Optional<List<Transaction>> allTransactionsByCourseId = transactionService.getAllTransactionsByCourseId(id);
        if (allTransactionsByCourseId.isPresent() && allTransactionsByCourseId.get().size() > 0){
            Optional<Course> courseById = courseService.getCourseById(id);
            model.addAttribute("course", courseById.get());
            model.addAttribute("transactions", allTransactionsByCourseId.get());
        } else {
            Optional<Course> courseById = courseService.getCourseById(id);
            model.addAttribute("course", courseById.get());
        }
        return "pages/user-course/courseDetails";
    }

//    @GetMapping("/transactions")
//    String getAllTransactions(Model model){
//        Optional<List<Client>> allClients = clientService.getAllClients();
//        Optional<List<Course>> allCourses = courseService.getAllCourses();
//        if (allClients.isPresent() && allCourses.isPresent()){
////            List<String> clientUserNames = allClients.get().stream().map(Client::getUserName).collect(Collectors.toList());
////            List<String> courseNames = allCourses.get().stream().map(Course::getName).collect(Collectors.toList());
//            model.addAttribute("clients", allClients.get());
//            model.addAttribute("courses", allCourses.get());
//        }
//        return "pages/user-course/alternative";
//    }

    @GetMapping("/transactions")
    public String getAllTransactions(Model model){
        List<String> userNames = clientService.getAllClientsUsernames();
        List<String> courseNames = courseService.getAllCoursesNames();
        if (userNames.size() > 0 && courseNames.size() > 0){
            model.addAttribute("userNames", userNames);
            model.addAttribute("courseNames", courseNames);
        }
        return "pages/user-course/alternative";
    }

    @PostMapping("/transactions")
    public String saveTransaction(TransactionSaveDTO transactionSaveDTO){
        transactionService.saveTransaction(transactionSaveDTO);
        return "redirect:/";
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

    //    @GetMapping("/transactions")
//    ResponseEntity<List<TransactionDTO>> getAllTransactions(){
//        return transactionService.getAllTransactions()
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
}