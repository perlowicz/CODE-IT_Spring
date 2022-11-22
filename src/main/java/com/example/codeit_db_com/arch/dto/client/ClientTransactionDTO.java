package com.example.codeit_db_com.arch.dto.client;

import java.util.List;

public class ClientTransactionDTO extends SimpleClientDTO{

    private List<TransactionClientDTO> transactions;

    public List<TransactionClientDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionClientDTO> transactions) {
        this.transactions = transactions;
    }
}
