package com.example.codeit_db_com.arch.client;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    @Query("SELECT c.name FROM Client cl JOIN cl.courses c WHERE cl.id = :clientId")
    Optional<String> getCourseNameForClient(long clientId);
}