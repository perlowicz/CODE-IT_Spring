package com.example.codeit_db_com.arch;

import com.example.codeit_db_com.arch.client.Client;
import com.example.codeit_db_com.arch.client.ClientRepository;
import com.example.codeit_db_com.arch.course.Course;
import com.example.codeit_db_com.arch.course.CourseRepository;
import com.example.codeit_db_com.arch.transaction.Transaction;
import com.example.codeit_db_com.arch.transaction.TransactionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class CodeItDbComApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CodeItDbComApplication.class, args);
        CourseRepository courseRepository = context.getBean(CourseRepository.class);
        ClientRepository clientRepository = context.getBean(ClientRepository.class);
        TransactionRepository transactionRepository = context.getBean(TransactionRepository.class);

        Client client1 = clientRepository.findById(1L).get();
        Client client2 = clientRepository.findById(2L).get();
        Course course1 = courseRepository.findById(1L).get();
        Course course2 = courseRepository.findById(2L).get();
        Transaction transaction = new Transaction(client1, course1, LocalDate.EPOCH, LocalDate.MAX, "Good opinion");
        Transaction secondTransaction = new Transaction(client1, course2, LocalDate.EPOCH, LocalDate.MAX, "Bad opinion");
        Transaction thirdTransaction = new Transaction(client2, course2, LocalDate.EPOCH, LocalDate.MAX, null);

        transactionRepository.save(transaction);
        transactionRepository.save(secondTransaction);
        transactionRepository.save(thirdTransaction);
//        System.out.println(client1);
//        System.out.println(course1);
//        client1.addTransaction(course1);
//        clientRepository.save(client1);
//
//        System.out.println("Zakupione kursy dla uzytkownika o id 1 -> " + clientRepository.getCourseNameForClient(1L).get());

    }

}
