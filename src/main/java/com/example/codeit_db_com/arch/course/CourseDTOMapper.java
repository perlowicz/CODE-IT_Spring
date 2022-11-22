package com.example.codeit_db_com.arch.course;

import com.example.codeit_db_com.arch.dto.CourseTransactionDTO;
import com.example.codeit_db_com.arch.dto.SimpleClientDTO;
import com.example.codeit_db_com.arch.dto.SimpleCourseDTO;
import com.example.codeit_db_com.arch.transaction.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseDTOMapper {

    CourseDTO map(Course course){
        CourseDTO courseDTO = new CourseDTO();
        List<CourseTransactionDTO> mappedTransactions = mapTransactions(course.getTransactions());

        courseDTO.setTransactions(mappedTransactions);
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setPrice(course.getPrice());

        return courseDTO;
    }

    private static List<CourseTransactionDTO> mapTransactions(List<Transaction> transactions){
        return transactions.stream().map(
                transaction -> {
                    SimpleClientDTO simpleClientDTO = new SimpleClientDTO();
                    CourseTransactionDTO courseTransactionDTO = new CourseTransactionDTO();

                    simpleClientDTO.setId(transaction.getClient().getId());
                    simpleClientDTO.setUserName(transaction.getClient().getUserName());
                    simpleClientDTO.setEmail(transaction.getClient().getEmail());
                    simpleClientDTO.setPassword(transaction.getClient().getPassword());
                    simpleClientDTO.setRegistrationDate(transaction.getClient().getRegistrationDate());

                    courseTransactionDTO.setClient(simpleClientDTO);
                    courseTransactionDTO.setId(transaction.getId());
                    courseTransactionDTO.setOpinion(transaction.getOpinion());
                    courseTransactionDTO.setSignupDate(transaction.getSignupDate());
                    courseTransactionDTO.setExpirationDate(transaction.getExpirationDate());

                    return courseTransactionDTO;
                }
        ).collect(Collectors.toList());
    }
}
