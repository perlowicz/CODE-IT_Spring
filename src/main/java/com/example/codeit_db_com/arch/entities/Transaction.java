package com.example.codeit_db_com.arch.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "client_id"
    )
    @NotNull
    private Client client;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(
            name = "course_id"
    )
    @NotNull
    private Course course;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private LocalDate signupDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
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

    public Transaction() { }

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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", client=" + client +
                ", course=" + course +
                ", signupDate=" + signupDate +
                ", expirationDate=" + expirationDate +
                ", opinion='" + opinion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id.equals(that.id) &&
                client.equals(that.client) &&
                course.equals(that.course) &&
                signupDate.equals(that.signupDate) &&
                expirationDate.equals(that.expirationDate) &&
                Objects.equals(opinion, that.opinion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, course, signupDate, expirationDate, opinion);
    }
}