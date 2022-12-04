package com.example.codeit_db_com.arch.transaction;

import com.example.codeit_db_com.arch.client.Client;
import com.example.codeit_db_com.arch.client.ClientRepository;
import com.example.codeit_db_com.arch.course.Course;
import com.example.codeit_db_com.arch.course.CourseRepository;
import com.example.codeit_db_com.arch.dto.TransactionSaveDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ClientRepository clientRepository;
    private final CourseRepository courseRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              ClientRepository clientRepository,
                              CourseRepository courseRepository) {
        this.transactionRepository = transactionRepository;
        this.clientRepository = clientRepository;
        this.courseRepository = courseRepository;
    }

    Optional<Transaction> getTransactionById(Long id){
        return transactionRepository.findById(id);
    }

    Optional<List<Transaction>> getAllTransactions(){
        List<Transaction> resultList = new ArrayList<>();
        transactionRepository.findAll().forEach(resultList::add);
        return Optional.of(resultList);
    }

    Optional<List<Transaction>> getAllTransactionsByUserId(Long id){
        List<Transaction> transactionList = new ArrayList<>();
        transactionRepository.getTransactionByClient_Id(id).forEach(transactionList::add);
        return Optional.of(transactionList);
    }

    Optional<List<Transaction>> getAllTransactionsByCourseId(Long id){
        List<Transaction> transactionList = new ArrayList<>();
        transactionRepository.getTransactionByCourse_Id(id).forEach(transactionList::add);
        return Optional.of(transactionList);
    }

    Transaction saveTransaction(TransactionSaveDTO transactionSaveDTO){
        Optional<Client> clientByUserName = clientRepository.findByUserName(transactionSaveDTO.getClientName());
        Optional<Course> courseByName = courseRepository.findCourseByName(transactionSaveDTO.getCourseName());
        Transaction transaction = new Transaction();
        if(clientByUserName.isPresent() && courseByName.isPresent()){
            transaction.setClient(clientByUserName.get());
            transaction.setCourse(courseByName.get());
            transaction.setSignupDate(LocalDate.now());
            transaction.setExpirationDate(LocalDate.now().plusYears(1));
            if (!transactionSaveDTO.getOpinion().equals(""))
                transaction.setOpinion(transactionSaveDTO.getOpinion());
            else
                transaction.setOpinion("brak opinii");
        }
        Transaction savedTransaction = transactionRepository.save(transaction);
        return savedTransaction;
    }
}
