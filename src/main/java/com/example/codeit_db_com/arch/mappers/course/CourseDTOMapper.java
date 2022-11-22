package com.example.codeit_db_com.arch.mappers.course;

import com.example.codeit_db_com.arch.course.Course;
import com.example.codeit_db_com.arch.dto.client.SimpleClientDTO;
import com.example.codeit_db_com.arch.dto.course.CourseTransactionDTO;
import com.example.codeit_db_com.arch.dto.course.TransactionCourseDTO;
import com.example.codeit_db_com.arch.transaction.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseDTOMapper {

    public CourseTransactionDTO map(Course course){
        CourseTransactionDTO courseTransactionDTO = new CourseTransactionDTO();
        List<TransactionCourseDTO> mappedTransactions = mapTransactions(course.getTransactions());

        courseTransactionDTO.setTransactions(mappedTransactions);
        courseTransactionDTO.setId(course.getId());
        courseTransactionDTO.setName(course.getName());
        courseTransactionDTO.setDescription(course.getDescription());
        courseTransactionDTO.setPrice(course.getPrice());

        return courseTransactionDTO;
    }

    private static List<TransactionCourseDTO> mapTransactions(List<Transaction> transactions){
        return transactions.stream().map(
                transaction -> {
                    SimpleClientDTO simpleClientDTO = new SimpleClientDTO();
                    TransactionCourseDTO transactionDTO = new TransactionCourseDTO();

                    simpleClientDTO.setId(transaction.getClient().getId());
                    simpleClientDTO.setUserName(transaction.getClient().getUserName());
                    simpleClientDTO.setEmail(transaction.getClient().getEmail());
                    simpleClientDTO.setPassword(transaction.getClient().getPassword());
                    simpleClientDTO.setRegistrationDate(transaction.getClient().getRegistrationDate());


                    transactionDTO.setClient(simpleClientDTO);
                    transactionDTO.setId(transaction.getId());
                    transactionDTO.setOpinion(transaction.getOpinion());
                    transactionDTO.setSignupDate(transaction.getSignupDate());
                    transactionDTO.setExpirationDate(transaction.getExpirationDate());

                    return transactionDTO;
                }
        ).collect(Collectors.toList());
    }
}
