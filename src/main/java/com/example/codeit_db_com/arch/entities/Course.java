package com.example.codeit_db_com.arch.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 50, max = 200)
    private String description;

    @NotNull
    @Positive
    @Min(value = 200)
    private Double price;

    @OneToMany(
            mappedBy = "course",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    private List<Transaction> transactions = new ArrayList<>();

    public Course(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.equals(course.id) &&
                name.equals(course.name) &&
                description.equals(course.description) &&
                price.equals(course.price) &&
                Objects.equals(transactions, course.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, transactions);
    }
}
