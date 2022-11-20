package com.example.codeit_db_com.arch.client;


import com.example.codeit_db_com.arch.course.Course;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String email;
    private String password;
    private LocalDate registrationDate;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "client_course",
            joinColumns = {
                @JoinColumn(name = "client_id", referencedColumnName = "id")
            },
                inverseJoinColumns = {
                @JoinColumn(name = "course_id", referencedColumnName = "id")
            }
    )
    private List<Course> courses = new ArrayList<>();

    public Client(String userName, String email, String password, LocalDate registrationDate) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public Client() {
    }

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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course){
        courses.add(course);
        course.addClient(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

//    <?xml version="1.0" encoding="UTF-8"?>
//<databaseChangeLog
//        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
//                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//                xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
//                https://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.2.xsd">
//<changeSet id="0001" author="perlowicz">
//<createTable tableName="client">
//<column name="id" type="BIGINT" autoIncrement="true">
//<constraints nullable="false" primaryKey="true"/>
//</column>
//<column name="user_name" type="VARCHAR(50)"/>
//<column name="email" type="VARCHAR(100)"/>
//<column name="password" type="VARCHAR(100)"/>
//<column name="registration_date" type="DATE"/>
//</createTable>
//</changeSet>
//<changeSet id="0002" author="perlowicz">
//<createTable tableName="course">
//<column name="id" type="BIGINT" autoIncrement="true">
//<constraints nullable="false" primaryKey="true"/>
//</column>
//<column name="name" type="VARCHAR(50)"/>
//<column name="description" type="VARCHAR(200)"/>
//<column name="price" type="DOUBLE"/>
//</createTable>
//</changeSet>
//<changeSet id="0003" author="perlowicz">
//<createTable tableName="client_courses">
//<column name="id" type="BIGINT" autoIncrement="true">
//<constraints nullable="false" primaryKey="true"/>
//</column>
//<column name="client_id" type="BIGINT">
//<constraints nullable="false"/>
//</column>
//<column name="course_id" type="VARCHAR(200)">
//<constraints nullable="false"/>
//</column>
//<column name="signup_date" type="DATE"/>
//<column name="expiration_date" type="DATE"/>
//<column name="opinion" type="VARCHAR(200)">
//<constraints nullable="true"/>
//</column>
//</createTable>
//<addForeignKeyConstraint baseTableName="client_course"
//        baseColumnNames="client_id"
//        constraintName="fk_client_id"
//        referencedTableName="client"
//        referencedColumnNames="id"/>
//<addForeignKeyConstraint baseTableName="client_course"
//        baseColumnNames="course_id"
//        constraintName="fk_course_id"
//        referencedTableName="course"
//        referencedColumnNames="id"/>
//</changeSet>
//<include file="../testdata/data.sql" relativeToChangelogFile="true"/>
//</databaseChangeLog>
