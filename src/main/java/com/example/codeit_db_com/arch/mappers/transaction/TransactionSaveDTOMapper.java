package com.example.codeit_db_com.arch.mappers.transaction;

import com.example.codeit_db_com.arch.client.Client;
import com.example.codeit_db_com.arch.client.ClientRepository;
import com.example.codeit_db_com.arch.course.Course;
import com.example.codeit_db_com.arch.course.CourseRepository;
import com.example.codeit_db_com.arch.dto.transaction.TransactionSaveDTO;
import com.example.codeit_db_com.arch.transaction.Transaction;
import org.springframework.stereotype.Service;

@Service
public class TransactionSaveDTOMapper {

    private final ClientRepository clientRepository;
    private final CourseRepository courseRepository;

    public TransactionSaveDTOMapper(ClientRepository clientRepository, CourseRepository courseRepository) {
        this.clientRepository = clientRepository;
        this.courseRepository = courseRepository;
    }

    public Transaction map(TransactionSaveDTO transactionSaveDTO){
        Transaction transaction = new Transaction();

        transaction.setId(transactionSaveDTO.getId());
        transaction.setOpinion(transactionSaveDTO.getOpinion());
        transaction.setSignupDate(transactionSaveDTO.getSignupDate());
        transaction.setExpirationDate(transactionSaveDTO.getExpirationDate());

        Client client = clientRepository.findByUserName(transactionSaveDTO.getClient_name()).orElseThrow();
        Course course = courseRepository.findByName(transactionSaveDTO.getCourse_name()).orElseThrow();

        transaction.setClient(client);
        transaction.setCourse(course);

        return transaction;
    }

    public TransactionSaveDTO map(Transaction transaction){
        TransactionSaveDTO transactionSaveDTO = new TransactionSaveDTO();

        transactionSaveDTO.setId(transaction.getId());
        transactionSaveDTO.setClient_name(transaction.getClient().getUserName());
        transactionSaveDTO.setCourse_name(transaction.getCourse().getName());
        transactionSaveDTO.setSignupDate(transaction.getSignupDate());
        transactionSaveDTO.setSignupDate(transaction.getSignupDate());
        transactionSaveDTO.setExpirationDate(transaction.getExpirationDate());
        transactionSaveDTO.setOpinion(transaction.getOpinion());

        return transactionSaveDTO;
    }
}
