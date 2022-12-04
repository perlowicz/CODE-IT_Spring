package com.example.codeit_db_com.arch.repositories;

import com.example.codeit_db_com.arch.entities.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findByUserName(String name);
}