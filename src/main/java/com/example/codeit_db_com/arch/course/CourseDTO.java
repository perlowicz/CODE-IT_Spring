package com.example.codeit_db_com.arch.course;

import com.example.codeit_db_com.arch.dto.CourseTransactionDTO;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private List<CourseTransactionDTO> transactions = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CourseTransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<CourseTransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
