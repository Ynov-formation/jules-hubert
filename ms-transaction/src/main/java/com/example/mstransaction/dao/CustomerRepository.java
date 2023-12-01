package com.example.mstransaction.dao;

import com.example.mstransaction.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
    CustomerEntity findByEmail(String email);

    @Override
    boolean existsById(UUID uuid);

    @Override
    void deleteById(UUID uuid);
}

