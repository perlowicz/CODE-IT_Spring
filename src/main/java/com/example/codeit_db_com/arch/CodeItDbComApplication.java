package com.example.codeit_db_com.arch;

import com.example.codeit_db_com.arch.client.Client;
import com.example.codeit_db_com.arch.client.ClientRepository;
import com.example.codeit_db_com.arch.course.Course;
import com.example.codeit_db_com.arch.course.CourseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class CodeItDbComApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CodeItDbComApplication.class, args);
        CourseRepository courseRepository = context.getBean(CourseRepository.class);
        ClientRepository clientRepository = context.getBean(ClientRepository.class);

        Client client1 = clientRepository.findById(1L).get();
        Course course1 = courseRepository.findById(1L).get();
        System.out.println(client1);
        System.out.println(course1);
        client1.addCourse(course1);
        clientRepository.save(client1);

        System.out.println("Zakupione kursy dla uzytkownika o id 1 -> " + clientRepository.getCourseNameForClient(1L).get());
    }

}
