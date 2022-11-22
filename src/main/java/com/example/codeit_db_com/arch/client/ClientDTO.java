package com.example.codeit_db_com.arch.client;

import com.example.codeit_db_com.arch.dto.ClientTransactionDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientDTO {

    private Long id;
    private String userName;
    private String email;
    private String password;
    private LocalDate registrationDate;
    private List<ClientTransactionDTO> transactions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<ClientTransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<ClientTransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
