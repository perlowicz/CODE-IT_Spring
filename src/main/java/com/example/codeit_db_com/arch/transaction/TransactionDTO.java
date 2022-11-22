package com.example.codeit_db_com.arch.transaction;

import com.example.codeit_db_com.arch.client.ClientDTO;
import com.example.codeit_db_com.arch.course.CourseDTO;
import com.example.codeit_db_com.arch.dto.SimpleClientDTO;
import com.example.codeit_db_com.arch.dto.SimpleCourseDTO;

import java.time.LocalDate;

public class TransactionDTO {

    private Long id;
    private SimpleClientDTO client;
    private SimpleCourseDTO course;
    private LocalDate signupDate;
    private LocalDate expirationDate;
    private String opinion;

    public SimpleClientDTO getClient() {
        return client;
    }

    public void setClient(SimpleClientDTO client) {
        this.client = client;
    }

    public SimpleCourseDTO getCourse() {
        return course;
    }

    public void setCourse(SimpleCourseDTO course) {
        this.course = course;
    }

    public LocalDate getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(LocalDate signupDate) {
        this.signupDate = signupDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
