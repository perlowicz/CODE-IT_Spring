package com.example.codeit_db_com.arch.transaction;

import com.example.codeit_db_com.arch.client.Client;
import com.example.codeit_db_com.arch.client.ClientDTO;
import com.example.codeit_db_com.arch.course.Course;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "client_id"
    )
    private Client client;
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "course_id"
    )
    private Course course;
    private LocalDate signupDate;
    private LocalDate expirationDate;
    private String opinion;

    public Transaction(Client client,
                       Course course,
                       LocalDate signupDate,
                       LocalDate expirationDate,
                       String opinion) {
        this.client = client;
        this.course = course;
        this.signupDate = signupDate;
        this.expirationDate = expirationDate;
        this.opinion = opinion;
    }

    public Transaction() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
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