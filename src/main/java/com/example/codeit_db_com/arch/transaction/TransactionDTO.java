package com.example.codeit_db_com.arch.transaction;

import com.example.codeit_db_com.arch.client.ClientDTO;
import com.example.codeit_db_com.arch.course.CourseDTO;

import java.time.LocalDate;

public class TransactionDTO {

    private ClientDTO clientDTO;
    private CourseDTO courseDTO;
    private LocalDate signupDate;
    private LocalDate expirationDate;
    private String opinion;

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
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
