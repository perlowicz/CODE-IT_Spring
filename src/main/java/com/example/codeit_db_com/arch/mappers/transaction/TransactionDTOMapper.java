package com.example.codeit_db_com.arch.mappers.transaction;

import com.example.codeit_db_com.arch.client.Client;
import com.example.codeit_db_com.arch.client.ClientRepository;
import com.example.codeit_db_com.arch.course.Course;
import com.example.codeit_db_com.arch.course.CourseRepository;
import com.example.codeit_db_com.arch.dto.transaction.TransactionSaveDTO;
import com.example.codeit_db_com.arch.mappers.client.SimpleClientDTOMapper;
import com.example.codeit_db_com.arch.mappers.course.SimpleCourseDTOMapper;
import com.example.codeit_db_com.arch.transaction.Transaction;
import com.example.codeit_db_com.arch.dto.transaction.TransactionDTO;
import org.springframework.stereotype.Service;

@Service
public class TransactionDTOMapper {

    private final SimpleClientDTOMapper simpleClientDTOMapper;
    private final SimpleCourseDTOMapper simpleCourseDTOMapper;


    public TransactionDTOMapper(SimpleClientDTOMapper simpleClientDTOMapper,
                                 SimpleCourseDTOMapper simpleCourseDTOMapper) {
        this.simpleClientDTOMapper = simpleClientDTOMapper;
        this.simpleCourseDTOMapper = simpleCourseDTOMapper;
    }

    public TransactionDTO map(Transaction transaction){
        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setId(transaction.getId());
        transactionDTO.setOpinion(transaction.getOpinion());
        transactionDTO.setSignupDate(transaction.getSignupDate());
        transactionDTO.setExpirationDate(transaction.getExpirationDate());
        transactionDTO.setClient(simpleClientDTOMapper.map(transaction.getClient()));
        transactionDTO.setCourse(simpleCourseDTOMapper.map(transaction.getCourse()));

        return transactionDTO;
    }
//    Transaction map(TransactionSaveDTO transactionSaveDTO){
//        Transaction transaction = new Transaction();
//
//        transaction.setId(transactionSaveDTO.getId());
//        transaction.setSignupDate(transactionSaveDTO.getSignupDate());
//        transaction.setExpirationDate(transactionSaveDTO.getExpirationDate());
//        transaction.setOpinion(transactionSaveDTO.getOpinion());
//
//        Client client = clientRepository.findById(transactionSaveDTO.getUser_id()).orElseThrow();
//        Course course = courseRepository.findById(transactionSaveDTO.getCourse_id()).orElseThrow();
//
//        transaction.setClient(client);
//        transaction.setCourse(course);
//
//        return transaction;
//    }
//
//    TransactionSaveDTO mapToSave(Transaction transaction){
//        TransactionSaveDTO transactionSaveDTO = new TransactionSaveDTO();
//
//        transactionSaveDTO.setId(transaction.getId());
//        transactionSaveDTO.setUser_id(transaction.getClient().getId());
//        transactionSaveDTO.setCourse_id(transaction.getCourse().getId());
//        transactionSaveDTO.setExpirationDate(transaction.getExpirationDate());
//        transactionSaveDTO.setSignupDate(transaction.getSignupDate());
//        transactionSaveDTO.setOpinion(transaction.getOpinion());
//
//        return transactionSaveDTO;
//    }
}
