package com.example.codeit_db_com.arch.dto;

import java.time.LocalDate;

public class ClientTransactionDTO {

    private Long id;
    private SimpleCourseDTO course;
    private LocalDate signupDate;
    private LocalDate expirationDate;
    private String opinion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
