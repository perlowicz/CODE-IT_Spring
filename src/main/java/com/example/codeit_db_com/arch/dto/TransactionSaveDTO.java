package com.example.codeit_db_com.arch.dto;
//class made for Thymeleaf stuff needs

public class TransactionSaveDTO {

    private String clientName;
    private String courseName;
    private String opinion;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    @Override
    public String toString() {
        return "TransactionSaveDTO{" +
                "clientName='" + clientName + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
