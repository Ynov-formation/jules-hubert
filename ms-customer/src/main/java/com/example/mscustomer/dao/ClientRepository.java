package com.example.mscustomer.dao;

import com.example.mscustomer.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}