package com.example.codeit_db_com.arch.service;

import com.example.codeit_db_com.arch.entities.Client;
import com.example.codeit_db_com.arch.repositories.ClientRepository;
import com.example.codeit_db_com.arch.entities.Course;
import com.example.codeit_db_com.arch.repositories.CourseRepository;
import com.example.codeit_db_com.arch.dto.TransactionSaveDTO;
import com.example.codeit_db_com.arch.entities.Transaction;
import com.example.codeit_db_com.arch.repositories.TransactionRepository;
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

    public Optional<List<Transaction>> getAllTransactionsByUserId(Long id){
        List<Transaction> transactionList = new ArrayList<>();
        transactionRepository.getTransactionByClient_Id(id).forEach(transactionList::add);
        return Optional.of(transactionList);
    }

    public Optional<List<Transaction>> getAllTransactionsByCourseId(Long id){
        List<Transaction> transactionList = new ArrayList<>();
        transactionRepository.getTransactionByCourse_Id(id).forEach(transactionList::add);
        return Optional.of(transactionList);
    }

    public Transaction saveTransaction(TransactionSaveDTO transactionSaveDTO){
        Optional<Client> clientFoundByUserName = clientRepository.findByUserName(transactionSaveDTO.getClientName());
        Optional<Course> courseFoundByName = courseRepository.findCourseByName(transactionSaveDTO.getCourseName());
        Transaction transaction = new Transaction();
        if(clientFoundByUserName.isPresent() && courseFoundByName.isPresent()){
            setTransactionAttributes(transaction, clientFoundByUserName.get(), courseFoundByName.get(), transactionSaveDTO);
        }
        return transactionRepository.save(transaction);
    }

    public boolean transactionAlreadyExists(TransactionSaveDTO transactionSaveDTO){
        Optional<Client> clientFoundByUserName = clientRepository.findByUserName(transactionSaveDTO.getClientName());
        Optional<Course> courseFoundByName = courseRepository.findCourseByName(transactionSaveDTO.getCourseName());
        if (clientFoundByUserName.isPresent() && courseFoundByName.isPresent())
            return transactionRepository.existsTransactionByClient_IdAndCourse_Id(
                    clientFoundByUserName.get().getId(),
                    courseFoundByName.get().getId());
        return false;
    }

    public void deleteTransaction(Long id){
        transactionRepository.deleteById(id);
    }

    private void setTransactionAttributes(Transaction transaction,
                                          Client client,
                                          Course course,
                                          TransactionSaveDTO transactionSaveDTO)
    {
        transaction.setClient(client);
        transaction.setCourse(course);
        transaction.setSignupDate(LocalDate.now());
        transaction.setExpirationDate(LocalDate.now().plusYears(1));
        if (!transactionSaveDTO.getOpinion().equals(""))
            transaction.setOpinion(transactionSaveDTO.getOpinion());
        else
            transaction.setOpinion("brak opinii");
    }
}
