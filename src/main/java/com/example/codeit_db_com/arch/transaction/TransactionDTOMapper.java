package com.example.codeit_db_com.arch.transaction;

import com.example.codeit_db_com.arch.client.Client;
import com.example.codeit_db_com.arch.client.ClientDTOMapper;
import com.example.codeit_db_com.arch.client.ClientRepository;
import com.example.codeit_db_com.arch.client.SimpleClientDTOMapper;
import com.example.codeit_db_com.arch.course.Course;
import com.example.codeit_db_com.arch.course.CourseDTOMapper;
import com.example.codeit_db_com.arch.course.CourseRepository;
import com.example.codeit_db_com.arch.course.SimpleCourseDTOMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionDTOMapper {

    private final SimpleClientDTOMapper simpleClientDTOMapper;
    private final SimpleCourseDTOMapper simpleCourseDTOMapper;
    private final ClientRepository clientRepository;
    private final CourseRepository courseRepository;


    private TransactionDTOMapper(SimpleClientDTOMapper simpleClientDTOMapper,
                                 SimpleCourseDTOMapper simpleCourseDTOMapper,
                                 ClientRepository clientRepository,
                                 CourseRepository courseRepository) {
        this.simpleClientDTOMapper = simpleClientDTOMapper;
        this.simpleCourseDTOMapper = simpleCourseDTOMapper;
        this.clientRepository = clientRepository;
        this.courseRepository = courseRepository;
    }

    TransactionDTO map(Transaction transaction){
        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setId(transaction.getId());
        transactionDTO.setOpinion(transaction.getOpinion());
        transactionDTO.setSignupDate(transaction.getSignupDate());
        transactionDTO.setExpirationDate(transaction.getExpirationDate());
        transactionDTO.setClient(simpleClientDTOMapper.map(transaction.getClient()));
        transactionDTO.setCourse(simpleCourseDTOMapper.map(transaction.getCourse()));

        return transactionDTO;
    }

    Transaction map(TransactionDTO transactionDTO){
        Transaction transaction = new Transaction();

        transaction.setId(transactionDTO.getId());
        transaction.setOpinion(transactionDTO.getOpinion());
        transaction.setSignupDate(transactionDTO.getSignupDate());
        transaction.setExpirationDate(transactionDTO.getExpirationDate());

        Client client = clientRepository.findById(simpleClientDTOMapper.map(transactionDTO.getClient()).getId()).orElseThrow();
        Course course = courseRepository.findById(simpleCourseDTOMapper.map(transactionDTO.getCourse()).getId()).orElseThrow();

        transaction.setClient(client);
        transaction.setCourse(course);

        return transaction;
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
