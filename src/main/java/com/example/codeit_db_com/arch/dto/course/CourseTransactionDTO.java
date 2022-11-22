package com.example.codeit_db_com.arch.dto.course;

import com.example.codeit_db_com.arch.dto.client.TransactionClientDTO;

import java.util.List;

public class CourseTransactionDTO extends SimpleCourseDTO{

    private List<TransactionCourseDTO> transactions;

    public List<TransactionCourseDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionCourseDTO> transactions) {
        this.transactions = transactions;
    }
}
